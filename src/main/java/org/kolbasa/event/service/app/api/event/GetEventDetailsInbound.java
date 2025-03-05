package org.kolbasa.event.service.app.api.event;

import org.kolbasa.event.service.domain.Event;

public interface GetEventDetailsInbound {
    Event execute(Long id);
}
