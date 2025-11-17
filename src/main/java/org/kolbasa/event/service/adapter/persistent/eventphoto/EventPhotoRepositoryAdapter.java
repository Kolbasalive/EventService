package org.kolbasa.event.service.adapter.persistent.eventphoto;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.repository.EventPhotoRepository;
import org.kolbasa.event.service.domain.EventPhoto;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventPhotoRepositoryAdapter implements EventPhotoRepository {
    private final EventPhotoJpaRepository repository;

    @Override
    public void save(EventPhoto eventPhoto) {
        repository.save(eventPhoto);
    }
}
