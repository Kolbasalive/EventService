package org.kolbasa.event.service.app.impl.auth;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.auth.JwtTokenService;
import org.kolbasa.event.service.app.api.auth.LoginInbound;
import org.kolbasa.event.service.app.api.event.dto.TokenPair;
import org.kolbasa.event.service.app.api.repository.EmployeeRepository;
import org.kolbasa.event.service.app.api.repository.TokenRepository;
import org.kolbasa.event.service.app.impl.exception.EmployeeNotFoundException;
import org.kolbasa.event.service.app.impl.exception.InvalidPasswordException;
import org.kolbasa.event.service.domain.Employee;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase implements LoginInbound {

    private final TokenRepository tokenRepository;
    private final EmployeeRepository employeeRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenPair execute(String login, String password) {
        Employee employee = getAndCheckEmployee(login, password);

        String accessToken = jwtTokenService.generateAccessToken(employee);
        String refreshToken = jwtTokenService.generateRefreshToken(employee);

        tokenRepository.save(refreshToken, employee.getEmployeeId());

        return new TokenPair(accessToken, refreshToken);
    }

    private Employee getAndCheckEmployee(String login, String password) {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(login));
        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new InvalidPasswordException(password);
        }
        return employee;
    }
}
