package org.kolbasa.event.service.app.api.review;

import org.kolbasa.event.service.app.api.review.dto.ResponseReviewDto;

import java.util.List;

public interface GetReviewForEventInbound {
    List<ResponseReviewDto> execute(Long eventId);
}