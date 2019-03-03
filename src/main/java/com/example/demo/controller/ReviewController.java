package com.example.demo.controller;

import com.example.demo.domain.Review;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity all() {
        List<Review> reviews = this.reviewService.findAll();
        //TODO: use proper DTO
        return ok(reviews);
    }

}
