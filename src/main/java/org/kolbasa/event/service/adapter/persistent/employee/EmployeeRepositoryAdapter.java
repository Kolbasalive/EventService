package org.kolbasa.event.service.adapter.persistent.employee;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.repository.EmployeeRepository;
import org.kolbasa.event.service.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepository {
    private final EmployeeJpaRepository repository;

    @Override
    public void save(Employee employee) {
        repository.save(employee);
    }

    @Override
    public Optional<Employee> findByLogin(String login) {
        return repository.findEmployeeByLogin(login);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }
}
