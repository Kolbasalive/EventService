package org.kolbasa.event.service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
public class Reservation {
    @Id
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
}
