package com.example.conference_management_system.repository;

import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.utils.PaperStatus;
import com.example.conference_management_system.utils.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepository_getUserByUserId_returnFoundUser(){

        User user = new User();
        user.setUserId(1);
        user.setUserRole(UserRole.Author);
        user.setPassword("password");
        user.setUsername("username");
        user.setEmail("email");

        // act
        Optional<User> result = userRepository.getUserByUserId(user.getUserId());

        // assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.get().getUserId()).isGreaterThan(0);

    }

    @Test
    public void userRepository_getUserByEmailAndPassword_returnFoundUser(){

        User user = new User();
        user.setUserRole(UserRole.Author);
        user.setPassword("password1");
        user.setUsername("author1");
        user.setEmail("author1@example.com");

        // act
        Optional<User> result = userRepository
                .getUserByEmailAndPassword(user.getEmail(), user.getPassword());

        // assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.get().getUserId()).isGreaterThan(0);

    }
}