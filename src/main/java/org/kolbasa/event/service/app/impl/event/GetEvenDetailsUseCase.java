package org.kolbasa.event.service.app.impl.event;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.event.GetEventDetailsInbound;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.kolbasa.event.service.app.impl.exception.EventNotFoundException;
import org.kolbasa.event.service.domain.Event;
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
