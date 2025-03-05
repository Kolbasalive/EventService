package org.kolbasa.event.service.app.impl;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.adapter.persistent.event.EventRepositoryAdapter;
import org.kolbasa.event.service.app.api.event.FindEventByIdInbound;
import org.kolbasa.event.service.domain.Event;
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
