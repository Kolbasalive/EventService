package org.kolbasa.eventhub.app.impl;

import lombok.RequiredArgsConstructor;
import org.kolbasa.eventhub.adapter.persistent.event.EventRepositoryAdapter;
import org.kolbasa.eventhub.app.api.event.FindEventByIdInbound;
import org.kolbasa.eventhub.domain.Event;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindEventByIdUseCase implements FindEventByIdInbound {
    private final EventRepositoryAdapter eventRepositoryAdapter;

    @Override
    public Optional<Event> execute(Long id) {
        return eventRepositoryAdapter.findById(id);
    }
}
