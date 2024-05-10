package com.example.conference_management_system.service.reviewService;

import com.example.conference_management_system.dto.UpdateReviewDTO;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.repository.PaperRepository;
import com.example.conference_management_system.repository.ReviewRepository;
import com.example.conference_management_system.utils.PaperStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private PaperRepository paperRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review review;
    private User reviewer;
    private Paper paper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        reviewer = new User();
        reviewer.setUserId(1);
        reviewer.setUsername("Reviewer");

        review = new Review();
        review.setReviewId(1);
        review.setReviewer(reviewer);
        review.setComment("Initial Comment");
        review.setRating(3.5f);

        paper = new Paper();
        paper.setPaperId(1);
        paper.setStatus(PaperStatus.Pending);
    }

    @Test
    void findAllReviewsByReviewer_ReturnsReviewList() {
        when(reviewRepository.findAllByReviewer(reviewer)).thenReturn(Arrays.asList(review));

        List<Review> reviews = reviewService.findAllReviewsByReviewer(reviewer);

        assertNotNull(reviews);
        assertFalse(reviews.isEmpty());
        assertEquals(1, reviews.size());
        assertEquals(review, reviews.get(0));

        verify(reviewRepository).findAllByReviewer(reviewer);
    }

    @Test
    void updateReview_UpdatesAndReturnsOk() {
        UpdateReviewDTO dto = new UpdateReviewDTO();
        dto.setReviewId(review.getReviewId());
        dto.setComment("Updated Comment");
        dto.setRating("4.5");
        dto.setPaperId(paper.getPaperId());
        dto.setStatus("Accepted");

        when(reviewRepository.findReviewByReviewId(dto.getReviewId())).thenReturn(Optional.of(review));
        when(paperRepository.findPaperByPaperId(dto.getPaperId())).thenReturn(Optional.of(paper));

        String response = reviewService.updateReview(dto);

        assertEquals("ok", response);
        verify(reviewRepository).save(review);
        verify(paperRepository).save(paper);

        assertEquals("Updated Comment", review.getComment());
        assertEquals(4.5f, review.getRating());
        assertEquals(PaperStatus.Accepted, paper.getStatus());
    }
}
