package org.kolbasa.event.service.app.impl.review;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.repository.ReviewRepository;
import org.kolbasa.event.service.app.api.review.GetReviewsInbound;
import org.kolbasa.event.service.app.api.review.dto.ResponseReviewDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetReviewsUseCase implements GetReviewsInbound {
    private final ReviewRepository reviewRepository;

    @Override
    public List<ResponseReviewDto> execute(Integer count) {
        return List.of();
    }
}
