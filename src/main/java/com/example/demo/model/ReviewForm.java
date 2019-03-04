package com.example.demo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReviewForm {

    private long id;

    @Min(value = 0, message = "Parameter 'score' must be positive integer number.")
    @Max(value = 5, message = "Parameter 'score' must be less or equal to 5")
    private String score;

    private String comment;

    @NotNull(message = "Field 'unit_id' must not be null")
    private Long unit_id;

    public ReviewForm() {}

    public ReviewForm(long id, String score, String comment, Long unit_id) {
        this.id = id;
        this.score = score;
        this.comment = comment;
        this.unit_id = unit_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(Long unit_id) {
        this.unit_id = unit_id;
    }
}
