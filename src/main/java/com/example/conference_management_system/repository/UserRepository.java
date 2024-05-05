package com.example.conference_management_system.repository;

import com.example.conference_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserByUserId(int userId);

    Optional<User> getUserByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM User WHERE user_role = 'Reviewer' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<User> findRandomReviewer();
}
