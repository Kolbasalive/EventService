package org.kolbasa.event.service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Любой пользователь eventHub.
 * <p>
 * Содержит информацию о сотруднике, такую как имя, email, логин и роль.
 * */
@Getter
@Setter
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    @SequenceGenerator(name = "employee_generator" , sequenceName = "employee_sequence", allocationSize = 1)
    private Long employeeId;
    private String name;
    private String email;
    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}