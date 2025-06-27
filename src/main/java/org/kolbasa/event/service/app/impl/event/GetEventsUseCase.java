package org.kolbasa.event.service.app.impl.event;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.event.GetEventsInbound;
import org.kolbasa.event.service.app.api.event.dto.ResponseEventDto;
import org.kolbasa.event.service.app.api.event.mapper.EventMapper;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetEventsUseCase implements GetEventsInbound {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public List<ResponseEventDto> execute() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::eventToResponseEventDto)
                .toList();
    }
}
