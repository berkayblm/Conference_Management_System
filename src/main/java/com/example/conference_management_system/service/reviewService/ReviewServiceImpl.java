package com.example.conference_management_system.service.reviewService;

import com.example.conference_management_system.dto.UpdateReviewDTO;
import com.example.conference_management_system.entity.Paper;
import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.repository.PaperRepository;
import com.example.conference_management_system.repository.ReviewRepository;
import com.example.conference_management_system.utils.PaperStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PaperRepository paperRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, PaperRepository paperRepository) {
        this.reviewRepository = reviewRepository;
        this.paperRepository = paperRepository;
    }

    @Override
    public List<Review> findAllReviewsByReviewer(User reviewer) {

        List<Review> reviewList =
                reviewRepository
                        .findAllByReviewer(reviewer);

        return reviewList;
    }

    @Override
    public String updateReview(UpdateReviewDTO updateReviewDTO) {

        Optional<Review> review = reviewRepository
                .findReviewByReviewId(updateReviewDTO.getReviewId());

        review.get().setComment(updateReviewDTO.getComment());
        review.get().setRating(Float.parseFloat(updateReviewDTO.getRating()));

        reviewRepository.save(review.get()); // update review

        // update paper

        Optional<Paper> paper = paperRepository.findPaperByPaperId(updateReviewDTO.getPaperId());

        paper.get().setStatus(PaperStatus.valueOf(updateReviewDTO.getStatus()));
        paperRepository.save(paper.get());

        return "ok";

    }
}
