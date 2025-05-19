package org.kolbasa.event.service.app.api.review.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.kolbasa.event.service.domain.Employee;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ReviewDto {
    private String comment;
    private LocalDateTime createdAt;
    private Integer rating;
    private Employee employee;
    private Long eventId;
    private Long employeeId;
}