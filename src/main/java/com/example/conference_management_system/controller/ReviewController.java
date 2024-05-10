package com.example.conference_management_system.controller;

import com.example.conference_management_system.dto.UpdateReviewDTO;
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

    final
    ReviewService reviewService;

    final
    UserService userService;

    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("/{reviewerId}")
    public List<Review> getAllReviewsByReviewerId(@PathVariable int reviewerId) {

        User reviewer = userService.getUserById(reviewerId);

        List<Review> reviewList =  reviewService
                                        .findAllReviewsByReviewer(reviewer);

        return reviewList;
    }

    @PostMapping("/updateReview")
    public String updateReviewByReviewId(@RequestBody UpdateReviewDTO updateReviewDTO) {

        return reviewService.updateReview(updateReviewDTO);


    }
}
