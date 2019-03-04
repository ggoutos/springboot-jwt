package com.example.demo.controller;

import com.example.demo.domain.Review;
import com.example.demo.model.ReviewForm;
import com.example.demo.model.ReviewModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    UnitService unitService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity all() {
        List<Review> reviewList = this.reviewService.findAll();

        List<ReviewModel> reviewModelList = new ArrayList<>();
        reviewList.forEach(review -> reviewModelList.add(new ReviewModel(review)));

        return ok(reviewModelList);
    }

    @PostMapping("/save")
    public ResponseEntity<ReviewModel> save(@Valid @RequestBody ReviewForm form,
                                            @AuthenticationPrincipal UserDetails userDetails,
                                            BindingResult bindingResult,
                                            HttpServletRequest request) {

        Review newReview = new Review();

        try {
            newReview.setScore(Integer.parseInt(form.getScore()));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Parameter 'score' must be an integer.");
        }

        newReview.setComment(form.getComment());

        newReview.setUnit(unitService.findAllById(form.getUnit_id())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No units found for the requested parameters.")));

        newReview.setUser(userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No user found for the requested parameters.")));

        Review saved = reviewService.save(newReview);

        //TODO: update score of unit

        return ok(new ReviewModel(saved));

    }

}
