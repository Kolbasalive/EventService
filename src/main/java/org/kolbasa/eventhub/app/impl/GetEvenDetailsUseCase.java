package org.kolbasa.eventhub.app.impl;

import lombok.RequiredArgsConstructor;
import org.kolbasa.eventhub.app.api.event.GetEventDetailsInbound;
import org.kolbasa.eventhub.app.api.repository.EventRepository;
import org.kolbasa.eventhub.app.impl.exception.EventNotFoundException;
import org.kolbasa.eventhub.domain.Event;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetEvenDetailsUseCase implements GetEventDetailsInbound {
    private final EventRepository eventRepository;

    @Override
    public Event execute(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }
}
