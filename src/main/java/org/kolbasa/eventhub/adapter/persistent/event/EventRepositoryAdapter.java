package org.kolbasa.eventhub.adapter.persistent.event;

import lombok.RequiredArgsConstructor;
import org.kolbasa.eventhub.app.api.repository.EventRepository;
import org.kolbasa.eventhub.domain.Event;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepositoryAdapter implements EventRepository {
    private final EventJpaRepository eventJpaRepository;

    @Override
    public Event save(Event event) {
        return eventJpaRepository.save(event);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventJpaRepository.findById(id);
    }
}
