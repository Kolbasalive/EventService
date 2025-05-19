package org.kolbasa.event.service.app.api.review.dto;

import lombok.Data;
import org.kolbasa.event.service.domain.Employee;
import org.kolbasa.event.service.domain.Event;

import java.time.LocalDateTime;

@Data
public class ResponseReviewDto {
    private Long reviewId;
    private String comment;
    private LocalDateTime createdAt;
    private Integer rating;
    private Employee employee;
    private Event event;
}