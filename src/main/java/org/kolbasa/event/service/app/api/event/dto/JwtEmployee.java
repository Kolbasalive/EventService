package org.kolbasa.event.service.app.api.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtEmployee {
    private Long userId;
    private String role;
}