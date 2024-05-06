package com.example.conference_management_system.service.userService;

import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(String email, String password) {

        Optional<User> user = userRepository
                .getUserByEmailAndPassword(email,
                        password);

        if(user.isPresent()) {
            return user.get();
        }

        return null;

    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.getUserByUserId(id);
        if(user.isPresent()) return user.get();

        else return null;
    }
}
