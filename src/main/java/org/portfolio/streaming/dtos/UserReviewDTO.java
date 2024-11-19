package org.portfolio.streaming.dtos;

import org.portfolio.streaming.entities.Review;
import org.portfolio.streaming.entities.User;

public class UserReviewDTO {

    private String username;
    private String review;
    private Double rating;
    private Long userId;


    public UserReviewDTO(String username, String review, Double rating, Long userId) {
        this.username = username;
        this.review = review;
        this.rating = rating;
        this.userId = userId;
    }

    public UserReviewDTO(Review entity, User userEntity) {
        review = entity.getReview();
        rating = entity.getRating();
        username = userEntity.getEmail();
        userId = userEntity.getId();

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

    public Long getUserId() {
        return userId;
    }
}
