package org.kolbasa.event.service.adapter.messaging.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kolbasa.event.service.domain.exception.JsonSerializationException;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventNotificationMessage {
    private Long eventId;
    private String title;
    private LocalDateTime eventDate;
    private String location;
    private List<EmployeeDetails> employees;

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new JsonSerializationException(e.getMessage());
        }
    }
}