package com.example.conference_management_system.service.paperService;

import com.example.conference_management_system.dto.SubmittedPaper;
import com.example.conference_management_system.entity.Conference;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.repository.ConferenceRepository;
import com.example.conference_management_system.repository.PaperRepository;
import com.example.conference_management_system.repository.ReviewRepository;
import com.example.conference_management_system.repository.UserRepository;
import com.example.conference_management_system.utils.PaperStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PaperServiceImpl implements PaperService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    public PaperServiceImpl(UserRepository userRepository
            , ConferenceRepository conferenceRepository
            , PaperRepository paperRepository
            , ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.conferenceRepository = conferenceRepository;
        this.paperRepository = paperRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Paper submitPaper(SubmittedPaper submittedPaper)  {

        Optional<User> user =
                userRepository
                        .getUserByUserId(
                                submittedPaper
                                        .getSenderUserId());

        Optional<Conference> conference = conferenceRepository
                .getConferenceByConferenceId(
                        submittedPaper
                                .getConferenceId());

        PaperStatus status = PaperStatus.Pending;

        Paper paper = new Paper();
//        paper.setPaperId(1);
        paper.setPaperUrl(submittedPaper.getPaperUrl());
        paper.setTitle(submittedPaper.getTitle());
        paper.setPaperAbstract(submittedPaper.getPaperAbstract());
        paper.setKeywords(submittedPaper.getKeywords());
        paper.setStatus(status);
        conference.ifPresent(paper::setConference);
        user.ifPresent(paper::setSenderUser);

        paperRepository.save(paper);

        // random choice Reviewer
        Optional<User> reviewer = userRepository.findRandomReviewer();

        Review review = new Review();
//        review.setReviewId(1);
        review.setReviewer(reviewer.get());
        review.setPaper(paper);
        review.setRating(0);
        review.setComment("");

        reviewRepository.save(review);

        return paper;



    }
}
