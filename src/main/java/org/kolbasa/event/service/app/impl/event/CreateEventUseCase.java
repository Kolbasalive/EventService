package org.kolbasa.event.service.app.impl.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kolbasa.event.service.adapter.messaging.dto.EmployeeDetails;
import org.kolbasa.event.service.adapter.messaging.dto.EventNotificationMessage;
import org.kolbasa.event.service.adapter.http.controller.mapper.EmployeeMapper;
import org.kolbasa.event.service.app.api.event.dto.EventDto;
import org.kolbasa.event.service.app.api.event.dto.ResponseDto;
import org.kolbasa.event.service.app.api.event.mapper.EventMapper;
import org.kolbasa.event.service.app.api.event.CreateEventInbound;
import org.kolbasa.event.service.app.api.repository.EmployeeRepository;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.kolbasa.event.service.app.api.spi.EventNotificationPublisher;
import org.kolbasa.event.service.domain.exception.InvalidEventTimeException;
import org.kolbasa.event.service.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateEventUseCase implements CreateEventInbound {
    private static final Logger log = LoggerFactory.getLogger(CreateEventUseCase.class);
    private final EventRepository eventRepository;
    private final EmployeeRepository employeeRepository;
    private final EventMapper eventMapper;
    private final EmployeeMapper employeeMapper;
    private final EventNotificationPublisher eventNotificationPublisher;

    @Override
    public ResponseDto execute(EventDto eventDto) {
        if (!isValidTime(eventDto)) {
            log.debug("Invalid event time: {}", eventDto);
            throw new InvalidEventTimeException(eventDto.getEventDate());
        }
        Event event = saveEvent(eventDto);
        notifyEmployees(event);
        log.info("Create event, id: {}", event.getEventId());

        return eventMapper.eventToResponseDto(event);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    //TODO Check orgId

    private boolean isValidTime(EventDto eventDto) {
        LocalDateTime nowDate = LocalDateTime.now();
        return eventDto.getEventDate().isAfter(nowDate);
    }

    private Event saveEvent(EventDto eventDto){
        Event event = eventMapper.eventDtoToEvent(eventDto);
        return eventRepository.save(event);
    }

    private void notifyEmployees(Event event){
        List<EmployeeDetails> employeeDetails = getEmployeeDetails();
        EventNotificationMessage msg = eventMapper.eventAndEmployeesToEventNotificationMessage(event, employeeDetails);
        eventNotificationPublisher.publishNewEvent(msg);
    }

    private List<EmployeeDetails> getEmployeeDetails() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::employeeDetailsToEmployee)
                .toList();
    }
}