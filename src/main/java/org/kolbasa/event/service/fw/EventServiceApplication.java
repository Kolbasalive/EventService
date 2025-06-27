package org.kolbasa.event.service.fw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "org.kolbasa.event.service")
@EntityScan(basePackages = "org.kolbasa.event.service.domain")
@EnableJpaRepositories(basePackages = "org.kolbasa.event.service.adapter.persistent")
public class EventServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventServiceApplication.class, args);
    }
}