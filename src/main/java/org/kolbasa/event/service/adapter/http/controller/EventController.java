package org.kolbasa.event.service.adapter.http.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kolbasa.event.service.app.api.event.DeleteEventInbound;
import org.kolbasa.event.service.app.api.event.GetEventByIdInbound;
import org.kolbasa.event.service.app.api.event.GetEventsInbound;
import org.kolbasa.event.service.app.api.event.dto.EventDto;
import org.kolbasa.event.service.app.api.event.dto.ResponseDto;
import org.kolbasa.event.service.app.api.event.CreateEventInbound;
import org.kolbasa.event.service.app.api.event.dto.ResponseEventDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@Slf4j
public class EventController {
    private final CreateEventInbound createEventInbound;
    private final DeleteEventInbound deleteEventInbound;
    private final GetEventByIdInbound getEventByIdInbound;
    private final GetEventsInbound getEventsInbound;

    @PostMapping("/createEvent")
    public ResponseDto createEvent(@RequestBody EventDto eventDto) {
        return createEventInbound.execute(eventDto);
    }

    @GetMapping("/{id}")
    public ResponseEventDto getEvent(@PathVariable Long id) {
        return getEventByIdInbound.execute(id);
    }

    @GetMapping("/all")
    public List<ResponseEventDto> getEvents(){
        return getEventsInbound.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteEvent(@PathVariable Long id) {
        return deleteEventInbound.execute(id);
    }
}
