package org.kolbasa.event.service.adapter.event.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    private String title;
    private String description;
    private String location;
    private Integer maxUserSize;
    private LocalDateTime eventDate;
    private Long organizationId;
}
