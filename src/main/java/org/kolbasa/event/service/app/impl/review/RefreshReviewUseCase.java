package org.kolbasa.event.service.app.impl.review;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.kolbasa.event.service.app.api.repository.ReviewRepository;
import org.kolbasa.event.service.app.api.review.RefreshReviewInbound;
import org.kolbasa.event.service.app.api.review.dto.ResponseReviewDto;
import org.kolbasa.event.service.app.api.review.dto.ReviewDto;
import org.kolbasa.event.service.domain.Review;
import org.kolbasa.event.service.domain.exception.ReviewNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshReviewUseCase implements RefreshReviewInbound {
    private final ReviewRepository reviewRepository;
    private final EventRepository eventRepository;

    @Override
    public ResponseReviewDto execute(Long eventId, ReviewDto reviewDto) {
        Long reviewId = reviewDto.getReviewId();
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));

        return null;
    }
}
