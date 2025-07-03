package org.kolbasa.event.service.adapter.http.controller.mapper;

import org.kolbasa.event.service.adapter.messaging.dto.EmployeeDetails;
import org.kolbasa.event.service.domain.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDetails employeeDetailsToEmployee(Employee employee);
}