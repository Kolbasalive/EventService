package org.kolbasa.eventhub.adapter.event.mapper;

import org.kolbasa.eventhub.adapter.event.dto.EventDto;
import org.kolbasa.eventhub.adapter.event.dto.ResponseDto;
import org.kolbasa.eventhub.domain.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event eventDtoToEvent(EventDto eventDto);
    ResponseDto eventToResponseDto(Event event);
    EventDto eventDtoToEvent(Event event);
}
