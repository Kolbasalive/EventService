package org.kolbasa.event.service.app.impl.auth;

import lombok.extern.slf4j.Slf4j;
import org.kolbasa.event.service.app.api.auth.JwtTokenService;
import org.kolbasa.event.service.app.api.event.dto.JwtEmployee;
import org.kolbasa.event.service.domain.Employee;
import org.kolbasa.event.service.domain.exception.JwtValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@Slf4j
public class JwtTokenServiceImpl implements JwtTokenService {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenServiceImpl.class);
    //TODO Поместить в .env
    private final String ACCESS_TOKEN_SECRET = "superSecretKeyWhichShouldBeVeryLongLONGLONGLONGLONG";
    private final String REFRESH_TOKEN_SECRET = "verySuperSecretKeyWhichShouldBeVeryLongLONGLONGLONGLONG";
    private final long ACCESS_TOKEN_VALIDITY = 1000L * 60 * 15; // 15 минут
    private final long REFRESH_TOKEN_VALIDITY = 1000L * 60 * 60 * 24 * 7; // 7 дней
    private final Key signingKey = Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));


    public String generateAccessToken(Employee employee) {
        Date dateNow = new Date();
        Date expiryDate = new Date(dateNow.getTime() + ACCESS_TOKEN_VALIDITY);

        return Jwts.builder()
                .setSubject(String.valueOf(employee.getEmployeeId()))
                .setIssuedAt(dateNow)
                .setExpiration(expiryDate)
                .claim("role", employee.getRole())
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Employee employee) {
        Date dateNow = new Date();
        Date expiryDate = new Date(dateNow.getTime() + REFRESH_TOKEN_VALIDITY);

        return Jwts.builder()
                .setSubject(String.valueOf(employee.getEmployeeId()))
                .setIssuedAt(dateNow)
                .setExpiration(expiryDate)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public JwtEmployee parseToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Long id = Long.parseLong(claims.getSubject());
            String role = claims.get("role", String.class);

            return new JwtEmployee(id, role);
        }catch (io.jsonwebtoken.JwtException e) {
            throw new JwtValidationException(e.getMessage());
        }
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = ACCESS_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

