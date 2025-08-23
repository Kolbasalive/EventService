package org.kolbasa.event.service.app.impl.event;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.event.DeleteEventInbound;
import org.kolbasa.event.service.app.api.event.dto.ResponseDto;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.kolbasa.event.service.domain.Event;
import org.kolbasa.event.service.app.impl.exception.EventNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteEventUseCase implements DeleteEventInbound {
    private static final String EVENT_DELETE_SUCCESS = "Event deleted successfully";
    private final EventRepository eventRepository;

    @Override
    public ResponseDto execute(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        eventRepository.delete(event);
        return new ResponseDto(EVENT_DELETE_SUCCESS);
    }
}