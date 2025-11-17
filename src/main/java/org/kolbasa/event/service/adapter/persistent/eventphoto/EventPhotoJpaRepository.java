package org.kolbasa.event.service.adapter.persistent.eventphoto;

import org.kolbasa.event.service.domain.EventPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPhotoJpaRepository extends JpaRepository<EventPhoto, Long> {}