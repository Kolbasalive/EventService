package org.kolbasa.event.service.app.api.repository;

import org.kolbasa.event.service.domain.Event;

import java.util.Optional;

public interface EventRepository {
    Event save(Event event);

    Optional<Event> findById(Long id);
}
