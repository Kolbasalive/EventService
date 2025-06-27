package org.kolbasa.event.service.app.impl.auth;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.auth.LoginInbound;
import org.kolbasa.event.service.app.api.event.dto.TokenPair;
import org.kolbasa.event.service.app.api.repository.EmployeeRepository;
import org.kolbasa.event.service.app.api.repository.TokenRepository;
import org.kolbasa.event.service.domain.Employee;
import org.kolbasa.event.service.domain.exception.EmployeeNotFoundException;
import org.kolbasa.event.service.domain.exception.InvalidPasswordException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase implements LoginInbound {
    private final TokenRepository tokenRepository;
    private final EmployeeRepository employeeRepository;
    private final JwtTokenService jwtTokenService;

    @Override
    public TokenPair execute(String login, String password) {
        Employee employee = getAndCheckEmployee(login, password);

        String accessToken = jwtTokenService.generateAccessToken(employee);
        String refreshToken = jwtTokenService.generateRefreshToken(employee);

        tokenRepository.save(refreshToken, employee.getEmployeeId());

        return new TokenPair(accessToken, refreshToken);

    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private Employee getAndCheckEmployee(String login, String password) {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(login));
        if (!employee.getPassword().equals(password)) {
            throw new InvalidPasswordException(password);
        }

        return employee;
    }

}
