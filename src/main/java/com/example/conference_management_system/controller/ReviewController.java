package com.example.conference_management_system.controller;

import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.service.reviewService.ReviewService;
import com.example.conference_management_system.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @GetMapping("/{reviewerId}")
    public List<Review> getAllReviewsByReviewerId(@PathVariable int reviewerId) {

        User reviewer = userService.getUserById(reviewerId);

        List<Review> reviewList =  reviewService
                                        .findAllReviewsByReviewer(reviewer);

        return reviewList;
    }
}
