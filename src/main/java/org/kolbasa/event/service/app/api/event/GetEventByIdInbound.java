package org.kolbasa.event.service.app.api.event;

import org.kolbasa.event.service.app.api.event.dto.ResponseEventDto;

public interface GetEventByIdInbound {
    ResponseEventDto execute(Long id);
}
