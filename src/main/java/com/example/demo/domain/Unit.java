package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @Column(length = 120)
    private String image;

    @Column(length = 120)
    private String title;

    @Column(length = 120)
    private String region;

    @Column(length = 300)
    private String description;

    @Column(name = "cancellation_policy", length = 300)
    private String cancellationPolicy;

    @Column
    private double price;

    @Column
    private double score;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.REMOVE, targetEntity = Review.class, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Review> reviews;

    public Unit() {
    }

    public Unit(Long id, String image, String title, String region, String description, String cancellationPolicy, double price, int score, List<Review> reviews) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.region = region;
        this.description = description;
        this.cancellationPolicy = cancellationPolicy;
        this.price = price;
        this.score = score;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
