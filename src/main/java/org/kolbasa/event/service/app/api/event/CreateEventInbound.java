package org.kolbasa.event.service.app.api.event;

import org.kolbasa.event.service.domain.Event;

public interface CreateEventInbound {
    Event execute(Event event);
}
