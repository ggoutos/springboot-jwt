package com.example.demo.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private int score;

    @Column
    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit unit;

    public Review() {
    }

    public Review(long id, int score, String comment, User user, Unit unit) {
        this.id = id;
        this.score = score;
        this.comment = comment;
        this.user = user;
        this.unit = unit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return getId() == review.getId() &&
                getScore() == review.getScore() &&
                Objects.equals(getComment(), review.getComment()) &&
                Objects.equals(getUser(), review.getUser()) &&
                Objects.equals(getUnit(), review.getUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getScore(), getComment(), getUser(), getUnit());
    }
}
