package org.portfolio.streaming.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "tb_review")
public class Review {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User userReview;
    @ManyToOne
    @JoinColumn (name = "movie_id")
    private Movie movie;
    @Column(columnDefinition = "TEXT")
    private String review;
    private Double rating;


    public Review(Long id, Movie movie, Double rating, String review, User userReview) {
        this.id = id;
        this.movie = movie;
        this.rating = rating;
        this.review = review;
        this.userReview = userReview;
    }

    public Review(String review, Long id, Double rating) {
        this.review = review;
        this.id = id;
        this.rating = rating;
    }


    public Movie getMovie() {
        return movie;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getUserReview() {
        return userReview;
    }

    public void setUserReview(User userReview) {
        this.userReview = userReview;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
