package org.kolbasa.event.service.adapter.persistent.token;

import org.kolbasa.event.service.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenJpaRepository extends JpaRepository<RefreshToken, Long> {
//    List<RefreshToken> findAllByEmployeeAndExpiredFalseAndRevokedFalse(Employee employee);
    Optional<RefreshToken> findByToken(String token);
    void save(String token);
}
