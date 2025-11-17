package org.kolbasa.event.service.adapter.http.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://localhost:9000") // API MinIO
                .credentials("minioadmin", "minioadmin") // из docker-compose
                .build();
    }
}