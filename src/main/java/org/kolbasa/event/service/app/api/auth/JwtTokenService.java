package org.kolbasa.event.service.app.api.auth;

import org.kolbasa.event.service.app.api.event.dto.JwtEmployee;
import org.kolbasa.event.service.domain.Employee;

public interface JwtTokenService {
    public String generateAccessToken(Employee employee);

    public String generateRefreshToken(Employee employee);

    String extractUsername(String token);

    JwtEmployee parseToken(String token);
}
