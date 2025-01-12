package org.portfolio.streaming.factories;

import org.portfolio.streaming.dtos.UserReviewMinDTO;
import org.portfolio.streaming.entities.Movie;
import org.portfolio.streaming.entities.Review;
import org.portfolio.streaming.repositories.projections.UserReviewProjection;
import org.portfolio.streaming.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewFactory {

    @Autowired
    static RandomStringGenerator generator;


    public static Review getDefaultReview () {

        return new Review(1L, MovieFactory.defaultMovie(), 10.0, "GREAT MOVIE", UserFactory.getDefaultUser());

    }


    public static UserReviewMinDTO getDefaultUserReviewMinDTO () {

        Review defaultReview = getDefaultReview();
        Movie movie = MovieFactory.defaultMovie();
        return new UserReviewMinDTO(defaultReview.getUserReview().getUsername(), defaultReview.getUserReview().getId(), defaultReview.getReview(),
                defaultReview.getRating(), movie.getId());

    }

    public static UserReviewProjection getDefaultUserReviewProjection () {

        return new UserReviewProjectionImpl();

    }



     static class UserReviewProjectionImpl implements UserReviewProjection {



        @Override
        public String getUsername() {
            return UserFactory.getDefaultUser().getUsername();
        }

        @Override
        public String getReview() {
            return ReviewFactory.getDefaultReview().getReview();
        }

        @Override
        public Double getRating() {
            return ReviewFactory.getDefaultReview().getRating();
        }

        @Override
        public Long getUserId() {
            return UserFactory.getDefaultUser().getId();
        }

        @Override
        public Long getMovie() {
            return MovieFactory.defaultMovie().getId();
        }
    }



}
