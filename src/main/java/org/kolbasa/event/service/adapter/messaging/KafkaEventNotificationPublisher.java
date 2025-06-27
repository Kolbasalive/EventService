package org.kolbasa.event.service.adapter.messaging;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.adapter.messaging.dto.EventNotificationMessage;
import org.kolbasa.event.service.app.api.spi.EventNotificationPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventNotificationPublisher implements EventNotificationPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.topic.events}")
    private String topic;

    @Override
    public void publishNewEvent(EventNotificationMessage message) {
        kafkaTemplate.send(topic, message.toJson());
    }
}
