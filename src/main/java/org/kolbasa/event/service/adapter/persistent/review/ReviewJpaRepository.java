package org.kolbasa.event.service.adapter.persistent.review;

import org.kolbasa.event.service.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
}
