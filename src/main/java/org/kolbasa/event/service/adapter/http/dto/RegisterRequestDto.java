package org.kolbasa.event.service.adapter.http.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String login;
    private String password;
    private String name;
    private String email;
}
