package com.example.demo.controller;

import com.example.demo.domain.Review;
import com.example.demo.model.ReviewForm;
import com.example.demo.model.ReviewModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UnitService;
import com.example.demo.utils.ModelMapper;
import com.example.demo.utils.RestResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static com.example.demo.utils.Messages.*;
import static java.util.OptionalInt.of;
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
        List<ReviewModel> reviewModelList = ModelMapper.reviewListToReviewModelList(this.reviewService.findAll());
        return RestResponseBuilder.build(reviewModelList);
    }

    @PostMapping("/save")
    public ResponseEntity<ReviewModel> save(@Valid @RequestBody ReviewForm form,
                                            @AuthenticationPrincipal UserDetails userDetails) {

        Review newReview = reviewFormToReview(form, userDetails);
        ReviewModel saved = new ReviewModel(reviewService.save(newReview));

        //TODO: update score of unit

        return ok(saved);
    }

    private Review reviewFormToReview(ReviewForm form, UserDetails userDetails) {
        Review newReview = new Review();

        try {
            newReview.setScore(Integer.parseInt(form.getScore()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, SCORE_INT_MESSAGE);
        }

        newReview.setComment(form.getComment());

        newReview.setUnit(unitService.findById(form.getUnit_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NO_UNITS_MESSAGE)));

        newReview.setUser(userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NO_USER_MESSAGE)));

        return newReview;
    }

}
