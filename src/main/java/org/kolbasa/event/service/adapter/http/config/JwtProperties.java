package org.kolbasa.event.service.adapter.http.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {
    private String accessSecret = "superSecretKeyWhichShouldBeVeryLongLONGLONGLONGLONG";
    private String refreshSecret = "verySuperSecretKeyWhichShouldBeVeryLongLONGLONGLONGLONG";
    private long accessValidityMs = 1000L * 60 * 15;
    private long refreshValidityMs = 1000L * 60 * 60 * 24 * 7;
}
