package com.example.conference_management_system.service.reviewService;

import com.example.conference_management_system.repository.PaperRepository;
import com.example.conference_management_system.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceTest {

    private ReviewServiceImpl reviewService;
    private ReviewRepository reviewRepository;
    private PaperRepository paperRepository;

    @BeforeEach
    void setUp() {
        reviewRepository = Mockito.mock(ReviewRepository.class);
        paperRepository = Mockito.mock(PaperRepository.class);

        reviewService = new ReviewServiceImpl(reviewRepository, paperRepository);
    }

    @Test
    void findAllReviewsByReviewer() {
    }

    @Test
    void updateReview() {




    }
}