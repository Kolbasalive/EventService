package org.kolbasa.event.service.app.api.auth.mapper;

import org.kolbasa.event.service.adapter.http.dto.RegisterRequestDto;
import org.kolbasa.event.service.domain.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "role", constant = "USER")
    Employee employeeMapper(RegisterRequestDto registerRequestDto);
}
