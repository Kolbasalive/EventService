package org.kolbasa.event.service.app.api.event.mapper;

import org.kolbasa.event.service.adapter.messaging.dto.EmployeeDetails;
import org.kolbasa.event.service.adapter.messaging.dto.EventNotificationMessage;
import org.kolbasa.event.service.app.api.event.dto.EventDto;
import org.kolbasa.event.service.app.api.event.dto.ResponseDto;
import org.kolbasa.event.service.app.api.event.dto.ResponseEventDto;
import org.kolbasa.event.service.domain.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event eventDtoToEvent(EventDto eventDto);
    @Mapping(source = "eventId", target = "response")
    ResponseDto eventToResponseDto(Event event);
    EventDto eventDtoToEvent(Event event);
    ResponseEventDto eventToResponseEventDto(Event event);
    @Mapping(source = "employees", target = "employees")
    EventNotificationMessage eventAndEmployeesToEventNotificationMessage(Event event, List<EmployeeDetails> employees);
}