package org.kolbasa.eventhub.app.api.repository;

import org.kolbasa.eventhub.domain.Event;

import java.util.Optional;

public interface EventRepository {
    Event save(Event event);

    Optional<Event> findById(Long id);
}
