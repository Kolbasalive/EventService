package org.kolbasa.event.service.app.impl;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.event.CreateEventInbound;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.kolbasa.event.service.domain.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateEventUseCase implements CreateEventInbound {
    private final EventRepository eventRepository;

    @Override
    public Event execute(Event event) {
        if (checkDate(event)) {
            return eventRepository.save(event);
        } else {
            //TODO Ex?
            //throw new DataAccessException(event.getEventDate());
            return null;
        }
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    //TODO Check orgId

    private boolean checkDate(Event event) {
        var nowDate = LocalDateTime.now();
        return !event.getEventDate().isAfter(nowDate);
    }
}