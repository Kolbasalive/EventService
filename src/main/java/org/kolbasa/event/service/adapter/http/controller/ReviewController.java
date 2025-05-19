package org.kolbasa.event.service.adapter.http.controller;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.review.CreateReviewInbound;
import org.kolbasa.event.service.app.api.review.GetReviewForEventInbound;
import org.kolbasa.event.service.app.api.review.dto.ResponseReviewDto;
import org.kolbasa.event.service.app.api.review.dto.ReviewDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("review")
@RequiredArgsConstructor
public class ReviewController {
    private final CreateReviewInbound createReviewInbound;
    private final GetReviewForEventInbound getReviewForEventInbound;

    @GetMapping("/{eventId}")
    public List<ResponseReviewDto> findById(@PathVariable Long eventId) {
        return getReviewForEventInbound.execute(eventId);
    }

    @PostMapping("/create/{eventId}")
    public ResponseReviewDto create(@PathVariable Long eventId, @RequestBody ReviewDto reviewDto) {
        return createReviewInbound.execute(eventId, reviewDto);
    }
}