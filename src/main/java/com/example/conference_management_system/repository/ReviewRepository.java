package com.example.conference_management_system.repository;

import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByReviewer(User user);

    Optional<Review> findReviewByReviewId(int reviewId);
}
