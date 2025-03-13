package org.kolbasa.event.service.app.impl.event.mapper;

import org.junit.jupiter.api.Test;
import org.kolbasa.event.service.app.api.event.dto.EventDto;
import org.kolbasa.event.service.app.api.event.dto.ResponseDto;
import org.kolbasa.event.service.app.api.event.mapper.EventMapper;
import org.kolbasa.event.service.domain.Event;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventMapperTest {

    private final EventMapper eventMapper = Mappers.getMapper(EventMapper.class);

    @Test
    void shouldMapEventDtoToEvent() {
        // Given
        EventDto eventDto = new EventDto();
        eventDto.setEventId(1L);
        eventDto.setTitle("Tech Conference");
        eventDto.setDescription("Annual Tech Conference");
        eventDto.setLocation("New York");
        eventDto.setMaxUserSize(500);
        eventDto.setEventDate(LocalDateTime.of(2025, 5, 15, 10, 0));
        eventDto.setOrganizationId(100L);
        eventDto.setAvgRating(4.5);
        eventDto.setCategory("Technology");

        // When
        Event event = eventMapper.eventDtoToEvent(eventDto);

        // Then
        assertNotNull(event);
        assertEquals(eventDto.getEventId(), event.getEventId());
        assertEquals(eventDto.getTitle(), event.getTitle());
        assertEquals(eventDto.getDescription(), event.getDescription());
        assertEquals(eventDto.getLocation(), event.getLocation());
        assertEquals(eventDto.getMaxUserSize(), event.getMaxUserSize());
        assertEquals(eventDto.getEventDate(), event.getEventDate());
        assertEquals(eventDto.getOrganizationId(), event.getOrganizationId());
        assertEquals(eventDto.getAvgRating(), event.getAvgRating());
        assertEquals(eventDto.getCategory(), event.getCategory());
    }

    @Test
    void shouldMapEventToResponseDto() {
        // Given
        Event event = new Event();
        event.setEventId(1L);

        // When
        ResponseDto responseDto = eventMapper.eventToResponseDto(event);

        // Then
        assertNotNull(responseDto);
        assertEquals(event.getEventId().toString(), responseDto.getResponse());
    }

    @Test
    void shouldMapEventToEventDto() {
        // Given
        Event event = new Event();
        event.setEventId(2L);
        event.setTitle("Music Fest");
        event.setDescription("Live music event");
        event.setLocation("Los Angeles");
        event.setMaxUserSize(300);
        event.setEventDate(LocalDateTime.of(2025, 7, 20, 18, 30));
        event.setOrganizationId(200L);
        event.setAvgRating(4.8);
        event.setCategory("Music");

        // When
        EventDto eventDto = eventMapper.eventDtoToEvent(event);

        // Then
        assertNotNull(eventDto);
        assertEquals(event.getEventId(), eventDto.getEventId());
        assertEquals(event.getTitle(), eventDto.getTitle());
        assertEquals(event.getDescription(), eventDto.getDescription());
        assertEquals(event.getLocation(), eventDto.getLocation());
        assertEquals(event.getMaxUserSize(), eventDto.getMaxUserSize());
        assertEquals(event.getEventDate(), eventDto.getEventDate());
        assertEquals(event.getOrganizationId(), eventDto.getOrganizationId());
        assertEquals(event.getAvgRating(), eventDto.getAvgRating());
        assertEquals(event.getCategory(), eventDto.getCategory());
    }
}
