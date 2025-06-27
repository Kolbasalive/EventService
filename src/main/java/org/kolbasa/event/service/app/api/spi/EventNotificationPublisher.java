package org.kolbasa.event.service.app.api.spi;

import org.kolbasa.event.service.adapter.messaging.dto.EventNotificationMessage;

public interface EventNotificationPublisher {
    void publishNewEvent(EventNotificationMessage message);
}
