package org.portfolio.streaming.dtos;

import org.portfolio.streaming.entities.Review;
import org.portfolio.streaming.entities.User;
import org.portfolio.streaming.repositories.projections.UserReviewProjection;

public class UserReviewMinDTO {

    private String username;
    private Long userId;
    private String review;
    private Double rating;
    private Long movie;


    public UserReviewMinDTO(String username, Long userId, String review, Double rating, Long movie) {
        this.username = username;
        this.userId = userId;
        this.review = review;
        this.rating = rating;
        this.movie = movie;
    }


    public UserReviewMinDTO() {
    }

    public UserReviewMinDTO(String username, String review, Double rating, Long movie) {
        this.username = username;
        this.review = review;
        this.rating = rating;
        this.movie = movie;
    }

    public UserReviewMinDTO(UserReviewProjection projection) {

        username = projection.getUsername();
        review = projection.getReview();
        rating = projection.getRating();
    }

    public UserReviewMinDTO (Review entity, User userEntity, Long movie) {

        review = entity.getReview();
        rating = entity.getRating();
        username = userEntity.getEmail();
        userId = userEntity.getId();
        this.movie = movie;
    }


    public Long getMovie() {
        return movie;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getReview() {
        return review;
    }

    public Double getRating() {
        return rating;
    }
}
