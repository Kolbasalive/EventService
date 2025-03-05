package org.kolbasa.eventhub.app.api.event;

import org.kolbasa.eventhub.domain.Event;

import java.util.Optional;

public interface FindEventByIdInbound {
    Optional<Event> execute(Long id);
}
