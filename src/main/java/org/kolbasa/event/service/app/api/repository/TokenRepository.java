package org.kolbasa.event.service.app.api.repository;

import org.kolbasa.event.service.domain.Employee;
import org.kolbasa.event.service.domain.RefreshToken;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {
    void save(String refreshToken, Long userId);
    List<RefreshToken> findAllByEmployeeAndExpiredFalseAndRevokedFalse(Employee employee);
    Optional<RefreshToken> findByToken(String token);
}
