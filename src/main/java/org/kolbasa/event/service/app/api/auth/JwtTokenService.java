package org.kolbasa.event.service.app.api.auth;

import org.kolbasa.event.service.app.api.event.dto.JwtEmployee;
import org.kolbasa.event.service.domain.Employee;

public interface JwtTokenService {

    String generateAccessToken(Employee employee);

    String generateRefreshToken(Employee employee);

    String extractUsername(String token);

    JwtEmployee parseAccessToken(String token);

    Long parseRefreshToken(String token);
}
