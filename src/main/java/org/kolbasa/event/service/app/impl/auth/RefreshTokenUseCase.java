package org.kolbasa.event.service.app.impl.auth;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.auth.JwtTokenService;
import org.kolbasa.event.service.app.api.auth.RefreshInbound;
import org.kolbasa.event.service.app.api.event.dto.TokenPair;
import org.kolbasa.event.service.app.api.repository.EmployeeRepository;
import org.kolbasa.event.service.app.api.repository.TokenRepository;
import org.kolbasa.event.service.app.impl.exception.EmployeeNotFoundException;
import org.kolbasa.event.service.app.impl.exception.InvalidRefreshTokenException;
import org.kolbasa.event.service.domain.Employee;
import org.kolbasa.event.service.domain.RefreshToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenUseCase implements RefreshInbound {

    private final JwtTokenService jwtTokenService;
    private final TokenRepository tokenRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public TokenPair execute(String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new InvalidRefreshTokenException();
        }

        RefreshToken storedToken = tokenRepository.findByToken(refreshToken)
                .orElseThrow(InvalidRefreshTokenException::new);

        Long employeeId = jwtTokenService.parseRefreshToken(refreshToken);
        if (!employeeId.equals(storedToken.getUserId())) {
            throw new InvalidRefreshTokenException();
        }

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(String.valueOf(employeeId)));

        String accessToken = jwtTokenService.generateAccessToken(employee);
        String newRefreshToken = jwtTokenService.generateRefreshToken(employee);
        tokenRepository.save(newRefreshToken, employee.getEmployeeId());

        return new TokenPair(accessToken, newRefreshToken);
    }
}
