package org.kolbasa.eventhub.app.api.event;

import org.kolbasa.eventhub.domain.Event;

public interface CreateEventInbound {
    Event execute(Event event);
}
