package org.kolbasa.event.service.adapter.persistent.employee;

import org.kolbasa.event.service.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByLogin(String login);
}
