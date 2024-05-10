package com.example.conference_management_system.controller;

import com.example.conference_management_system.dto.UpdateReviewDTO;
import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.service.reviewService.ReviewService;
import com.example.conference_management_system.service.userService.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private UserService userService;

    private User reviewer;
    private Review review;

    @BeforeEach
    void setUp() {
        // Setup mock data
        reviewer = new User();
        reviewer.setUserId(1);
        reviewer.setUsername("ReviewerName");

        review = new Review();
        review.setReviewId(1);
        review.setComment("Good paper");
        review.setReviewer(reviewer);
    }

    @Test
    void getAllReviewsByReviewerId_ShouldReturnReviews() throws Exception {
        List<Review> reviews = Arrays.asList(review);
        given(userService.getUserById(1)).willReturn(reviewer);
        given(reviewService.findAllReviewsByReviewer(reviewer)).willReturn(reviews);

        mockMvc.perform(get("/api/reviews/{reviewerId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].comment").value("Good paper"));
    }

    @Test
    void updateReviewByReviewId_ShouldReturnSuccessMessage() throws Exception {
        UpdateReviewDTO updateReviewDTO = new UpdateReviewDTO();
        updateReviewDTO.setReviewId(review.getReviewId());
        updateReviewDTO.setComment("Excellent paper");
        given(reviewService.updateReview(updateReviewDTO)).willReturn("Review updated successfully");

        mockMvc.perform(post("/api/reviews/updateReview")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reviewId\":1,\"comment\":\"Excellent paper\"}")) // Example JSON body
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Review updated successfully"));
    }
}
