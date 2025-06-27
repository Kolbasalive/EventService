package org.kolbasa.event.service.fw;

import org.apache.kafka.clients.producer.*;
import java.util.*;

public class SimpleProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092"); // порт из docker-compose
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record =
                new ProducerRecord<>("events", "event-key", "Hello from EventService!");

        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.printf("Sent to topic %s partition %d offset %d%n",
                        metadata.topic(), metadata.partition(), metadata.offset());
            } else {
                exception.printStackTrace();
            }
        });

        producer.flush();
        producer.close();
    }
}

