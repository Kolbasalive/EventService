package org.kolbasa.event.service.app.api.repository;

import org.kolbasa.event.service.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);
    void delete(Review review);
    Optional<Review> findById(Long id);
    List<Review> findAll();
}
