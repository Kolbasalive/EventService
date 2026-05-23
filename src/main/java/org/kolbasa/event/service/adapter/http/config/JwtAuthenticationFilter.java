package org.kolbasa.event.service.adapter.http.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.auth.JwtTokenService;
import org.kolbasa.event.service.app.api.event.dto.JwtEmployee;
import org.kolbasa.event.service.app.impl.exception.JwtValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!dev")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtTokenService tokenService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/api/auth")
                || path.startsWith("/actuator")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            try {
                JwtEmployee jwtEmployee = tokenService.parseAccessToken(jwt);
                var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + jwtEmployee.getRole()));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(jwtEmployee, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("Authenticated user id={}", jwtEmployee.getUserId());
            } catch (JwtValidationException e) {
                SecurityContextHolder.clearContext();
                log.debug("Invalid JWT: {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
