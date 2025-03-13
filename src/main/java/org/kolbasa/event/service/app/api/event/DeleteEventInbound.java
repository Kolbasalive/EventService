package org.kolbasa.event.service.app.api.event;

import org.kolbasa.event.service.app.api.event.dto.ResponseDto;

public interface DeleteEventInbound {
    ResponseDto execute(Long id);
}