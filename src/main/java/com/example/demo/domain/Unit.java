package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "units")
public class Unit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

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

    @OneToMany(mappedBy = "unit", cascade = CascadeType.REMOVE, targetEntity = Review.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> reviews;

    public Unit() {
    }

    public Unit(long id, String image, String title, String region, String description, String cancellationPolicy, double price, int score, List<Review> reviews) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void updateScore() {
        this.score = setPrecision(getScore(), 2);
    }

    public double setPrecision(double value, int precision) {
        return BigDecimal.valueOf(value)
                .setScale(precision, RoundingMode.HALF_UP)
                .doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        Unit unit = (Unit) o;
        return getId() == unit.getId() &&
                Double.compare(unit.getPrice(), getPrice()) == 0 &&
                Double.compare(unit.getScore(), getScore()) == 0 &&
                Objects.equals(getImage(), unit.getImage()) &&
                Objects.equals(getTitle(), unit.getTitle()) &&
                Objects.equals(getRegion(), unit.getRegion()) &&
                Objects.equals(getDescription(), unit.getDescription()) &&
                Objects.equals(getCancellationPolicy(), unit.getCancellationPolicy()) &&
                Objects.equals(getReviews(), unit.getReviews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getImage(), getTitle(), getRegion(), getDescription(), getCancellationPolicy(), getPrice(), getScore(), getReviews());
    }

}
