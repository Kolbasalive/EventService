package org.kolbasa.event.service.app.api.event;

import org.kolbasa.event.service.app.api.event.dto.EventDto;
import org.kolbasa.event.service.app.api.event.dto.ResponseDto;

public interface CreateEventInbound {
    ResponseDto execute(EventDto event);
}
