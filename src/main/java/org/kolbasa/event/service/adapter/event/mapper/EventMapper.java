package org.kolbasa.event.service.adapter.event.mapper;

import org.kolbasa.event.service.adapter.event.dto.EventDto;
import org.kolbasa.event.service.adapter.event.dto.ResponseDto;
import org.kolbasa.event.service.domain.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event eventDtoToEvent(EventDto eventDto);
    @Mapping(source = "eventId", target = "response")
    ResponseDto eventToResponseDto(Event event);
    EventDto eventDtoToEvent(Event event);
}