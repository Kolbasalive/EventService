package org.kolbasa.event.service.adapter.http.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.adapter.http.dto.LoginRequest;
import org.kolbasa.event.service.adapter.http.dto.RegisterRequestDto;
import org.kolbasa.event.service.app.api.auth.LoginInbound;
import org.kolbasa.event.service.app.api.auth.RefreshInbound;
import org.kolbasa.event.service.app.api.auth.RegisterInbound;
import org.kolbasa.event.service.app.api.event.dto.ResponseDto;
import org.kolbasa.event.service.app.api.event.dto.TokenPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private static final String REFRESH_TOKEN_COOKIE = "refreshToken";

    private final LoginInbound loginInbound;
    private final RegisterInbound registerInbound;
    private final RefreshInbound refreshInbound;

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        log.info("login: {}", loginRequest.getLogin());
        TokenPair tokenPair = loginInbound.execute(loginRequest.getLogin(), loginRequest.getPassword());
        setRefreshTokenCookie(response, tokenPair.getRefreshToken());
        return new ResponseDto(tokenPair.getAccessToken());
    }

    @PostMapping("/register")
    public ResponseDto register(@RequestBody RegisterRequestDto registerRequestDto, HttpServletResponse response) {
        log.info("register: {}", registerRequestDto.getLogin());
        TokenPair tokenPair = registerInbound.execute(registerRequestDto);
        setRefreshTokenCookie(response, tokenPair.getRefreshToken());
        return new ResponseDto(tokenPair.getAccessToken());
    }

    @PostMapping("/refresh")
    public ResponseDto refresh(@CookieValue(name = REFRESH_TOKEN_COOKIE, required = false) String refreshToken,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        String token = refreshToken != null ? refreshToken : readRefreshTokenFromCookies(request);
        TokenPair tokenPair = refreshInbound.execute(token);
        setRefreshTokenCookie(response, tokenPair.getRefreshToken());
        return new ResponseDto(tokenPair.getAccessToken());
    }

    private String readRefreshTokenFromCookies(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (REFRESH_TOKEN_COOKIE.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private void setRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        Cookie refreshCookie = new Cookie(REFRESH_TOKEN_COOKIE, refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(refreshCookie);
    }
}
