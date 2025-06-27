package org.kolbasa.event.service.app.api.review;

import org.kolbasa.event.service.app.api.review.dto.ResponseReviewDto;
import org.kolbasa.event.service.app.api.review.dto.ReviewDto;

public interface RefreshReviewInbound {
    ResponseReviewDto execute(Long eventId, ReviewDto reviewDto);
}
