package org.kolbasa.event.service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Отзыв(рецензия) пользователя.
 * <p>
 * Содержит информацию о рецензии, такую как комментарий, дата создания, рейтинг,
 * а также ссылки на сотрудника и событие.
 * */
@Getter
@Setter
@ToString
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_generator")
    @SequenceGenerator(name = "review_generator" , sequenceName = "review_sequence", allocationSize = 1)
    private Long reviewId;
    private String comment;
    private LocalDateTime createdAt;
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}