package org.kolbasa.event.service.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Событие или мероприятие.
 * <p>
 * Содержит информацию о событии, такую как название, описание, место проведения, дату и другие параметры.
 * Также включает связь с рецензиями на это событие.
 */
@Getter
@Setter
@ToString
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
    @SequenceGenerator(name = "event_generator", sequenceName = "event_sequence", allocationSize = 1)
    private Long eventId;
    private String title;
    private String category;
    private String description;
    private String location;
    private Double avgRating;
    private Integer maxUserSize;
    private LocalDateTime eventDate;
    private Long organizationId;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
}