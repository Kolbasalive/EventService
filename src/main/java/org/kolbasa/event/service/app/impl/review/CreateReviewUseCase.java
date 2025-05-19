package org.kolbasa.event.service.app.impl.review;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.repository.ReviewRepository;
import org.kolbasa.event.service.app.api.review.CreateReviewInbound;
import org.kolbasa.event.service.app.api.review.dto.ResponseReviewDto;
import org.kolbasa.event.service.app.api.review.dto.ReviewDto;
import org.kolbasa.event.service.app.api.review.mapper.ReviewMapper;
import org.kolbasa.event.service.domain.Review;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateReviewUseCase implements CreateReviewInbound {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ResponseReviewDto execute(Long eventId, ReviewDto reviewDto) {
        reviewDto.setEventId(eventId);
        Review review = reviewMapper.reviewDtoToReview(reviewDto);
        review = reviewRepository.save(review);

        return reviewMapper.reviewToResponseReviewDto(review);
    }
}
