package org.kolbasa.event.service.app.impl.review;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.kolbasa.event.service.app.api.review.GetReviewForEventInbound;
import org.kolbasa.event.service.app.api.review.dto.ResponseReviewDto;
import org.kolbasa.event.service.app.api.review.mapper.ReviewMapper;
import org.kolbasa.event.service.domain.Event;
import org.kolbasa.event.service.domain.Review;
import org.kolbasa.event.service.domain.exception.EventNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetReviewForEventUseCase implements GetReviewForEventInbound {
    private final EventRepository eventRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ResponseReviewDto> execute(Long eventId) {
        Event a = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));
        List<Review> reviews = a.getReviews();

        return reviews.stream()
                .map(reviewMapper::reviewToResponseReviewDto)
                .toList();
    }
}