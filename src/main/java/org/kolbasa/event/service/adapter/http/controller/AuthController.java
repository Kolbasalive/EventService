package org.kolbasa.event.service.adapter.http.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.adapter.http.dto.LoginRequest;
import org.kolbasa.event.service.adapter.http.dto.RegisterRequestDto;
import org.kolbasa.event.service.app.api.auth.LoginInbound;
import org.kolbasa.event.service.app.api.auth.RegisterInbound;
import org.kolbasa.event.service.app.api.event.dto.ResponseDto;
import org.kolbasa.event.service.app.api.event.dto.TokenPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final LoginInbound loginInbound;
    private final RegisterInbound registerInbound;

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        log.info("login: {}", loginRequest);
        TokenPair tokenPair = loginInbound.execute(loginRequest.getLogin(), loginRequest.getPassword());
        createCookie(response, tokenPair.getRefreshToken());

        return new ResponseDto(tokenPair.getAccessToken());
    }

    @PostMapping("/register")
    public ResponseDto register(@RequestBody RegisterRequestDto registerRequestDto, HttpServletResponse response) {
        log.info("register: {}", registerRequestDto);
        TokenPair tokenPair = registerInbound.execute(registerRequestDto);
        createCookie(response, tokenPair.getRefreshToken());

        return new ResponseDto(tokenPair.getAccessToken());
    }

    private void createCookie(HttpServletResponse response, String refreshToken) {
        Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60); // 7 дней
        response.addCookie(refreshCookie);
    }
}
