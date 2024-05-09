package com.example.conference_management_system.repository;

import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.utils.PaperStatus;
import com.example.conference_management_system.utils.UserRole;
import org.assertj.core.api.Assertions;
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

    @Test
    public void reviewRepository_saveAll_returnSavedReview(){

        // arrange
        User reviewer = new User();
        reviewer.setUserRole(UserRole.Reviewer);
        reviewer.setPassword("password");
        reviewer.setUsername("username");
        reviewer.setEmail("email");

        User author = new User();
        author.setUserRole(UserRole.Author);
        author.setPassword("password");
        author.setUsername("username");
        author.setEmail("email");

        Conference conference = Conference.builder()
                .theme("theme")
                .title("title")
                .date(null)
                .location("location")
                .build();

        Paper paper = new Paper();
        paper.setConference(conference);
        paper.setStatus(PaperStatus.Pending);
        paper.setPaperUrl("url");
        paper.setKeywords("keywords");
        paper.setSenderUser(author);

        Review review = Review.builder()
                .reviewer(reviewer)
                .paper(paper)
                .comment("comment")
                .rating(0)
                .build();

        // act
        Review result = reviewRepository.save(review);

        // assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getReviewId()).isGreaterThan(0);

    }

    @Test
    public void reviewRepository_findReviewByReviewId_returnSavedReview(){

        // arrange
        User reviewer = new User();
        reviewer.setUserRole(UserRole.Reviewer);
        reviewer.setPassword("password");
        reviewer.setUsername("username");
        reviewer.setEmail("email");

        User author = new User();
        author.setUserRole(UserRole.Author);
        author.setPassword("password");
        author.setUsername("username");
        author.setEmail("email");

        Conference conference = Conference.builder()
                .theme("theme")
                .title("title")
                .date(null)
                .location("location")
                .build();

        Paper paper = new Paper();
        paper.setConference(conference);
        paper.setStatus(PaperStatus.Pending);
        paper.setPaperUrl("url");
        paper.setKeywords("keywords");
        paper.setSenderUser(author);

        Review review = Review.builder()
                .reviewer(reviewer)
                .paper(paper)
                .comment("comment")
                .rating(0)
                .build();

        // act
        Optional<Review> result = reviewRepository
                .findReviewByReviewId(review.getReviewId());

        // assert
        Assertions.assertThat(result).isNotNull();

    }

    @Test
    public void reviewRepository_findAllByReviewer_returnListOfReviews(){

        // arrange
        User reviewer = new User();
        reviewer.setUserId(1);
        reviewer.setUserRole(UserRole.Reviewer);
        reviewer.setPassword("password");
        reviewer.setUsername("username");
        reviewer.setEmail("email");

        User author = new User();
        author.setUserRole(UserRole.Author);
        author.setPassword("password");
        author.setUsername("username");
        author.setEmail("email");

        Conference conference = Conference.builder()
                .theme("theme")
                .title("title")
                .date(null)
                .location("location")
                .build();

        Paper paper = new Paper();
        paper.setConference(conference);
        paper.setStatus(PaperStatus.Pending);
        paper.setPaperUrl("url");
        paper.setKeywords("keywords");
        paper.setSenderUser(author);

        // act
        List<Review> result = reviewRepository
                .findAllByReviewer(reviewer);

        // assert
        Assertions.assertThat(result).isNotNull();

    }
}