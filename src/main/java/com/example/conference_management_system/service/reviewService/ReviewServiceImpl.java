package com.example.conference_management_system.service.reviewService;

import com.example.conference_management_system.entity.Review;
import com.example.conference_management_system.entity.User;
import com.example.conference_management_system.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> findAllReviewsByReviewer(User reviewer) {

        List<Review> reviewList =
                reviewRepository
                        .findAllByReviewer(reviewer);

        return reviewList;
    }
}
