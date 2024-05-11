package com.example.conference_management_system.service.paperService;

import com.example.conference_management_system.dto.SubmittedPaper;
import com.example.conference_management_system.dto.UserDetails;
import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.repository.ConferenceRepository;
import com.example.conference_management_system.repository.PaperRepository;
import com.example.conference_management_system.repository.ReviewRepository;
import com.example.conference_management_system.repository.UserRepository;
import com.example.conference_management_system.utils.PaperStatus;
import com.example.conference_management_system.utils.UserRole;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PaperServiceTest {
    private PaperServiceImpl paperService;
    private UserRepository userRepository;
    private ConferenceRepository conferenceRepository;
    private PaperRepository paperRepository;
    private ReviewRepository reviewRepository;
    @BeforeEach
    void setUp() {
        paperRepository = Mockito.mock(PaperRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        conferenceRepository = Mockito.mock(ConferenceRepository.class);
        reviewRepository = Mockito.mock(ReviewRepository.class);

        paperService = new PaperServiceImpl(userRepository, conferenceRepository
                , paperRepository, reviewRepository);
    }

    @Test
    void submitPaper() {

        User author = new User();
        author.setUserId(1);
        author.setEmail("email");
        author.setUsername("username");
        author.setPassword("password");
        author.setUserRole(UserRole.Author);

        User reviewer = new User();
        reviewer.setUserId(1);
        reviewer.setEmail("email");
        reviewer.setUsername("username");
        reviewer.setPassword("password");
        reviewer.setUserRole(UserRole.Reviewer);

        Conference conference = new Conference();
        conference.setConferenceId(1);
        conference.setTitle("conference");

        SubmittedPaper submittedPaper = new SubmittedPaper();
        submittedPaper.setPaperAbstract("abstract");
        submittedPaper.setKeywords("keyword");
        submittedPaper.setTitle("title");
        submittedPaper.setSenderUserId(author.getUserId());
        submittedPaper.setConferenceId(conference.getConferenceId());

        Paper paper = new Paper();
        paper.setPaperAbstract("abstract");
        paper.setPaperUrl(null);
        paper.setTitle("title");
        paper.setKeywords("keyword");
        paper.setSenderUser(author);
        paper.setConference(conference);
        paper.setStatus(PaperStatus.Pending);

        Review review = new Review();
        review.setPaper(paper);
        review.setReviewer(reviewer);
        review.setComment("");
        review.setRating(0);

        Mockito.when(paperRepository.save(paper)).thenReturn(paper);
        Mockito.when(conferenceRepository
                .getConferenceByConferenceId(conference.getConferenceId()))
                .thenReturn(Optional.of(conference));
        Mockito.when(userRepository.getUserByUserId(author.getUserId()))
                .thenReturn(Optional.of(author));
        Mockito.when(userRepository.findRandomReviewer())
                .thenReturn(Optional.of(reviewer));

        Paper result = paperService.submitPaper(submittedPaper);
        Assertions.assertEquals(result, paper);
        Mockito.verify(paperRepository).save(paper);

    }
}