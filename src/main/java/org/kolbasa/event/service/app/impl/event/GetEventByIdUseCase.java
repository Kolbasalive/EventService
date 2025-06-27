package org.kolbasa.event.service.app.impl.event;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.event.GetEventByIdInbound;
import org.kolbasa.event.service.app.api.event.dto.ResponseEventDto;
import org.kolbasa.event.service.app.api.event.mapper.EventMapper;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.kolbasa.event.service.domain.Event;
import org.kolbasa.event.service.domain.exception.EventNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetEventByIdUseCase implements GetEventByIdInbound {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public ResponseEventDto execute(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        return eventMapper.eventToResponseEventDto(event);
    }
}
