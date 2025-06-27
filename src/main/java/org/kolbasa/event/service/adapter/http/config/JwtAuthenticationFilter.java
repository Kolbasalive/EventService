package org.kolbasa.event.service.adapter.http.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kolbasa.event.service.app.api.event.dto.JwtEmployee;
import org.kolbasa.event.service.app.impl.auth.JwtTokenService;
import org.kolbasa.event.service.domain.exception.JwtValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtTokenService tokenService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.startsWith("/auth");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("Missing or invalid Authorization header");
            throw new JwtValidationException("Missing or invalid Authorization header");
        }
        String jwt = authHeader.substring(7);
        try {
            JwtEmployee jwtEmployee = tokenService.parseToken(jwt);
            log.info("jwtEmployee token: {}", jwtEmployee);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(jwtEmployee, null, List.of());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Authentication установлена: {}", authentication.isAuthenticated());

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            throw new BadCredentialsException(format("Invalid JWT token %s", e.getMessage()));
        }

        filterChain.doFilter(request, response);
    }
}
