package org.portfolio.streaming.dtos;

import org.portfolio.streaming.repositories.projections.UserReviewProjection;

public class UserReviewMinDTO {

    private String username;
    private String review;
    private Double rating;


    public UserReviewMinDTO(String username, String review, Double rating) {
        this.username = username;
        this.review = review;
        this.rating = rating;
    }


    public UserReviewMinDTO(UserReviewProjection projection) {

        username = projection.getUsername();
        review = projection.getReview();
        rating = projection.getRating();
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
