package org.kolbasa.event.service.app.api.event;

import org.kolbasa.event.service.domain.Event;

import java.util.Optional;

public interface FindEventByIdInbound {
    Optional<Event> execute(Long id);
}
