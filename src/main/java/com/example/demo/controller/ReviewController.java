package com.example.demo.controller;

import com.example.demo.domain.Review;
import com.example.demo.domain.Unit;
import com.example.demo.domain.User;
import com.example.demo.model.ReviewForm;
import com.example.demo.model.ReviewModel;
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

import static com.example.demo.utils.Messages.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    UnitService unitService;

    @GetMapping("")
    public ResponseEntity all() {
        List<ReviewModel> reviewModelList = ModelMapper.reviewListToReviewModelList(this.reviewService.findAll());
        return RestResponseBuilder.build(reviewModelList);
    }

    @PostMapping("/save")
    public ResponseEntity<ReviewModel> save(@Valid @RequestBody ReviewForm form,
                                            @AuthenticationPrincipal UserDetails userDetails) {

        Review newReview = reviewFormToReview(form, userDetails);
        Review saved = reviewService.save(newReview);

        Unit updatedUnit = unitService.updateScoreByReview(saved);
        saved.setUnit(updatedUnit);

        ReviewModel result = new ReviewModel(saved);
        return ok(result);
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

        //update previous review of the user for the specific unit
        List<Review> reviews = newReview.getUnit().getReviews();
        for (Review previous : reviews ) {
            if (previous.getUser().getUsername().equals(userDetails.getUsername())){
                newReview.setId(previous.getId());
            }
        }

        newReview.setUser((User) userDetails);

        return newReview;
    }

}
