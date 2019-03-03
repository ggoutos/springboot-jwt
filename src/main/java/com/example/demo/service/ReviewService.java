package com.example.demo.service;

import com.example.demo.domain.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

}
