package org.kolbasa.event.service.app.impl.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kolbasa.event.service.app.api.event.dto.EventDto;
import org.kolbasa.event.service.app.api.event.dto.ResponseDto;
import org.kolbasa.event.service.app.api.event.mapper.EventMapper;
import org.kolbasa.event.service.app.api.event.CreateEventInbound;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.kolbasa.event.service.domain.exception.InvalidEventTimeException;
import org.kolbasa.event.service.domain.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateEventUseCase implements CreateEventInbound {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public ResponseDto execute(EventDto eventDto) {
        if (!isValidTime(eventDto)) {
            throw new InvalidEventTimeException(eventDto.getEventDate());
        }
        Event event = eventMapper.eventDtoToEvent(eventDto);
        event = eventRepository.save(event);

        return eventMapper.eventToResponseDto(event);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    //TODO Check orgId

    private boolean isValidTime(EventDto eventDto) {
        LocalDateTime nowDate = LocalDateTime.now();
        return eventDto.getEventDate().isAfter(nowDate);
    }
}