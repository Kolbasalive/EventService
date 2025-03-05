package org.kolbasa.eventhub.adapter.event;

import lombok.RequiredArgsConstructor;
import org.kolbasa.eventhub.adapter.event.dto.EventDto;
import org.kolbasa.eventhub.adapter.event.dto.ResponseDto;
import org.kolbasa.eventhub.adapter.event.mapper.EventMapper;
import org.kolbasa.eventhub.app.api.event.CreateEventInbound;
import org.kolbasa.eventhub.app.api.event.GetEventDetailsInbound;
import org.kolbasa.eventhub.domain.Event;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventAdapter {
    private final EventMapper eventMapper;
    private final CreateEventInbound createEventInbound;
    private final GetEventDetailsInbound getEventDetailsInbound;

    @PostMapping("/createEvent")
    public ResponseDto createEvent(@RequestBody EventDto eventDto) {
        Event event = createEventInbound.execute(eventMapper.eventDtoToEvent(eventDto));
        return eventMapper.eventToResponseDto(event);
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable String id) {
        var event = getEventDetailsInbound.execute(Long.valueOf(id));
        return eventMapper.eventDtoToEvent(event);
    }
}
