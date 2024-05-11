package com.example.conference_management_system.repository;

import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.utils.PaperStatus;
import com.example.conference_management_system.utils.UserRole;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;
    private User reviewer;
    private User author;
    private Conference conference;
    private Paper paper;
    private Review review;
    @BeforeEach
    public void setup(){
        reviewer = User.builder().userRole(UserRole.Reviewer).password("password")
                .username("username").email("email").userId(1).build();

        author = User.builder().userRole(UserRole.Author).password("password")
                .username("username").email("email").build();

        Conference conference = Conference.builder().theme("theme").title("title")
                .date(null).location("location").build();

        paper = Paper.builder().conference(conference).status(PaperStatus.Pending)
                .paperUrl("url").keywords("keywords").senderUser(author).build();

        review = Review.builder().reviewer(reviewer).paper(paper).comment("comment")
                .rating(0).build();
    }

    @Test
    public void reviewRepository_saveAll_returnSavedReview(){
        // act
        Review result = reviewRepository.save(review);

        // assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getReviewId()).isGreaterThan(0);

    }

    @Test
    public void reviewRepository_findReviewByReviewId_returnSavedReview(){
        Optional<Review> result = reviewRepository
                .findReviewByReviewId(review.getReviewId());

        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void reviewRepository_findAllByReviewer_returnListOfReviews(){
        // act
        List<Review> result = reviewRepository
                .findAllByReviewer(reviewer);

        // assert
        Assertions.assertThat(result).isNotNull();

    }
}