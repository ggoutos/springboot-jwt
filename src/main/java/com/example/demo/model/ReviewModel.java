package com.example.demo.model;

import com.example.demo.domain.Review;
import com.example.demo.domain.Unit;

public class ReviewModel {

    private Long id;
    private int score;
    private String username;
    private Unit unit;

    public ReviewModel() {
    }

    public ReviewModel(Long id, int score, String username, Unit unit) {
        this.id = id;
        this.score = score;
        this.username = username;
        this.unit = unit;
    }

    public ReviewModel(Review review) {
        this.id = review.getId();
        this.score = review.getScore();
        this.username = review.getUser().getUsername();
        this.unit = review.getUnit();
        unit.updateScore();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
