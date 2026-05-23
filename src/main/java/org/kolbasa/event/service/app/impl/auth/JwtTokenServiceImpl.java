package org.kolbasa.event.service.app.impl.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.adapter.http.config.JwtProperties;
import org.kolbasa.event.service.app.api.auth.JwtTokenService;
import org.kolbasa.event.service.app.api.event.dto.JwtEmployee;
import org.kolbasa.event.service.app.impl.exception.JwtValidationException;
import org.kolbasa.event.service.domain.Employee;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(Employee employee) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getAccessValidityMs());

        return Jwts.builder()
                .setSubject(String.valueOf(employee.getEmployeeId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("role", employee.getRole().name())
                .signWith(accessSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateRefreshToken(Employee employee) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getRefreshValidityMs());

        return Jwts.builder()
                .setSubject(String.valueOf(employee.getEmployeeId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(refreshSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return parseClaims(token, accessSigningKey()).getSubject();
    }

    @Override
    public JwtEmployee parseAccessToken(String token) {
        try {
            Claims claims = parseClaims(token, accessSigningKey());
            Long id = Long.parseLong(claims.getSubject());
            String role = claims.get("role", String.class);
            if (role == null) {
                throw new JwtValidationException("Access token is missing role claim");
            }
            return new JwtEmployee(id, role);
        } catch (io.jsonwebtoken.JwtException | NumberFormatException e) {
            throw new JwtValidationException(e.getMessage());
        }
    }

    @Override
    public Long parseRefreshToken(String token) {
        try {
            Claims claims = parseClaims(token, refreshSigningKey());
            return Long.parseLong(claims.getSubject());
        } catch (io.jsonwebtoken.JwtException | NumberFormatException e) {
            throw new JwtValidationException(e.getMessage());
        }
    }

    private Claims parseClaims(String token, Key signingKey) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key accessSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getAccessSecret().getBytes(StandardCharsets.UTF_8));
    }

    private Key refreshSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getRefreshSecret().getBytes(StandardCharsets.UTF_8));
    }
}
