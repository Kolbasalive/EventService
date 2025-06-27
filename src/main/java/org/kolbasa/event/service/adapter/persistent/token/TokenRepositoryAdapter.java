package org.kolbasa.event.service.adapter.persistent.token;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.repository.TokenRepository;
import org.kolbasa.event.service.domain.Employee;
import org.kolbasa.event.service.domain.RefreshToken;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryAdapter implements TokenRepository {
    private final TokenJpaRepository repository;

    @Override
    public void save(String refreshToken, Long userId) {
        RefreshToken token = new RefreshToken();
        token.setToken(refreshToken);
        token.setUserId(userId);

        repository.save(token);
    }
    @Override
    public List<RefreshToken> findAllByEmployeeAndExpiredFalseAndRevokedFalse(Employee employee) {
        return null;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token);
    }
}
