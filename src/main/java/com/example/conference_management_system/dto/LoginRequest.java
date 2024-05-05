package com.example.conference_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {

    private String email;
    private String password;
}
