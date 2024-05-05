package com.example.conference_management_system.service.userService;

import com.example.conference_management_system.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    User getUser(String email, String password);
}
