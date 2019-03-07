package com.example.demo.service;

import com.example.demo.domain.Review;
import com.example.demo.domain.Unit;
import com.example.demo.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    UnitRepository unitRepository;

    public Slice<Unit> findAllByTitleAndRegion(String title, String region, Pageable pageable) {
        return unitRepository.findAllByTitleAndRegion(title, region, pageable);
    }

    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    public Optional<Unit> findById(Long unit_id) {
        return unitRepository.findById(unit_id);
    }

    public Unit updateScoreByReview(Review review) {
        Unit unit = review.getUnit();
        List<Review> reviews = unit.getReviews();
        double newScore = reviews.stream().mapToInt(Review::getScore).sum() / (double) reviews.size();
        unit.setScore(newScore);
        return unitRepository.save(unit);
    }
}
