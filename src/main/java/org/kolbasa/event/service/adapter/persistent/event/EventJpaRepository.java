package org.kolbasa.event.service.adapter.persistent.event;

import org.kolbasa.event.service.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<Event, Long> {
}
