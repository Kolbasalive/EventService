package org.kolbasa.event.service.app.api.repository;

import org.kolbasa.event.service.domain.EventPhoto;

public interface EventPhotoRepository {
    void save(EventPhoto eventPhoto);
}