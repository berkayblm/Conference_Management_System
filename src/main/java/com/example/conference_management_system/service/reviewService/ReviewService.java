package com.example.conference_management_system.service.reviewService;

import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    List<Review> findAllReviewsByReviewer(User user);
}
