package org.kolbasa.event.service.app.api.event.dto;

import lombok.Data;
import org.kolbasa.event.service.domain.Review;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDto {
    private Long eventId;
    private String title;
    private String category;
    private String description;
    private String location;
    private Double avgRating;
    private Integer maxUserSize;
    private LocalDateTime eventDate;
    private Long organizationId;
    private List<Review> reviews;
}