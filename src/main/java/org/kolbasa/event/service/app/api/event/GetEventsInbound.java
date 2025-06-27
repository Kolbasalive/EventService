package org.kolbasa.event.service.app.api.event;

import org.kolbasa.event.service.app.api.event.dto.ResponseEventDto;

import java.util.List;

public interface GetEventsInbound {
    List<ResponseEventDto> execute();
}
