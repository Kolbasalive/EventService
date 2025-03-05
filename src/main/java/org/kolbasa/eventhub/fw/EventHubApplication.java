package org.kolbasa.eventhub.fw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = "org.kolbasa.eventhub")
@EntityScan(basePackages = "org.kolbasa.eventhub.domain")
@EnableJpaRepositories(basePackages = "org.kolbasa.eventhub.adapter.persistent")
public class EventHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventHubApplication.class, args);
    }
}
