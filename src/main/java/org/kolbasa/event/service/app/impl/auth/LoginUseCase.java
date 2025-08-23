package org.kolbasa.event.service.app.impl.auth;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.auth.LoginInbound;
import org.kolbasa.event.service.app.api.event.dto.TokenPair;
import org.kolbasa.event.service.app.api.repository.EmployeeRepository;
import org.kolbasa.event.service.app.api.repository.TokenRepository;
import org.kolbasa.event.service.domain.Employee;
import org.kolbasa.event.service.app.impl.exception.EmployeeNotFoundException;
import org.kolbasa.event.service.app.impl.exception.InvalidPasswordException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase implements LoginInbound {
    private final TokenRepository tokenRepository;
    private final EmployeeRepository employeeRepository;
    private final JwtTokenServiceImpl jwtTokenServiceImpl;

    @Override
    public TokenPair execute(String login, String password) {
        Employee employee = getAndCheckEmployee(login, password);

        String accessToken = jwtTokenServiceImpl.generateAccessToken(employee);
        String refreshToken = jwtTokenServiceImpl.generateRefreshToken(employee);

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
