package org.kolbasa.event.service.app.impl.auth;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.adapter.http.dto.RegisterRequestDto;
import org.kolbasa.event.service.app.api.auth.RegisterInbound;
import org.kolbasa.event.service.app.api.event.dto.TokenPair;
import org.kolbasa.event.service.adapter.http.controller.mapper.AuthMapper;
import org.kolbasa.event.service.app.api.repository.EmployeeRepository;
import org.kolbasa.event.service.app.api.repository.TokenRepository;
import org.kolbasa.event.service.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUseCase implements RegisterInbound {
    private final EmployeeRepository employeeRepository;
    private final TokenRepository tokenRepository;
    private final JwtTokenServiceImpl jwtTokenServiceImpl;
    private final AuthMapper authMapper;

    private static final Logger log = LoggerFactory.getLogger(RegisterUseCase.class);

    @Override
    public TokenPair execute(RegisterRequestDto registerRequestDto) {
        Employee employee = authMapper.employeeMapper(registerRequestDto);
        log.info("Employee: {}", employee);
        employeeRepository.save(employee);

        String accessToken = jwtTokenServiceImpl.generateAccessToken(employee);
        String refreshToken = jwtTokenServiceImpl.generateRefreshToken(employee);

        log.info("Refresh token: {}", refreshToken);
        log.info("Access token: {}", accessToken);
        tokenRepository.save(refreshToken, employee.getEmployeeId());

        return new TokenPair(accessToken, refreshToken);
    }
}