package com.example.conference_management_system.controller;

import com.example.conference_management_system.dto.LoginRequest;
import com.example.conference_management_system.dto.UserDetails;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.service.userService.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public UserDetails login(@RequestBody LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userService.getUser(email, password);

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(user.getUserId());
        userDetails.setUserName(user.getUsername());
        userDetails.setUserRole(user.getUserRole());


        return userDetails;


    }


}
