package org.kolbasa.event.service.adapter.messaging.dto;

import lombok.Data;

@Data
public class EmployeeDetails {
    private Long employeeId;
    private String name;
    private String email;
}