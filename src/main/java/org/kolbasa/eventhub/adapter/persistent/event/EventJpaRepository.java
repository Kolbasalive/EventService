package org.kolbasa.eventhub.adapter.persistent.event;

import org.kolbasa.eventhub.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<Event, Long> {
}
