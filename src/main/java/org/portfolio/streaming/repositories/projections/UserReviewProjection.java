package org.portfolio.streaming.repositories.projections;

public interface UserReviewProjection {

    String getUsername();

    String getReview();

    Double getRating();

    Long getUserId();

}
