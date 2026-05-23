package org.kolbasa.event.service.app.impl.auth;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.adapter.http.dto.RegisterRequestDto;
import org.kolbasa.event.service.app.api.auth.JwtTokenService;
import org.kolbasa.event.service.app.api.auth.RegisterInbound;
import org.kolbasa.event.service.app.api.event.dto.TokenPair;
import org.kolbasa.event.service.adapter.http.controller.mapper.AuthMapper;
import org.kolbasa.event.service.app.api.repository.EmployeeRepository;
import org.kolbasa.event.service.app.api.repository.TokenRepository;
import org.kolbasa.event.service.domain.Employee;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUseCase implements RegisterInbound {

    private final EmployeeRepository employeeRepository;
    private final TokenRepository tokenRepository;
    private final JwtTokenService jwtTokenService;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenPair execute(RegisterRequestDto registerRequestDto) {
        Employee employee = authMapper.employeeMapper(registerRequestDto);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);

        String accessToken = jwtTokenService.generateAccessToken(employee);
        String refreshToken = jwtTokenService.generateRefreshToken(employee);
        tokenRepository.save(refreshToken, employee.getEmployeeId());

        return new TokenPair(accessToken, refreshToken);
    }
}
