package com.example.conference_management_system.dto;

import com.example.conference_management_system.utils.UserRole;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    private int userId;
    private String userName;
    private UserRole userRole;
}
