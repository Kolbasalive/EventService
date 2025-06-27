package org.kolbasa.event.service.app.api.repository;

import org.kolbasa.event.service.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void save(Employee employee);
    Optional<Employee> findByLogin(String login);
    List<Employee> findAll();
}
