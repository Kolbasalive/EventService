package org.kolbasa.event.service.adapter.persistent.review;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.repository.ReviewRepository;
import org.kolbasa.event.service.domain.Review;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryAdapter implements ReviewRepository {
    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public Review save(Review review) {
        return null;
    }

    @Override
    public void delete(Review review) {
        reviewJpaRepository.delete(review);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewJpaRepository.findById(id);
    }

    @Override
    public List<Review> findAll() {
        return reviewJpaRepository.findAll();
    }
}