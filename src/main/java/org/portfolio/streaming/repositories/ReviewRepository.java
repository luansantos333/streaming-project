package org.portfolio.streaming.repositories;

import org.portfolio.streaming.entities.Review;
import org.portfolio.streaming.repositories.projections.UserReviewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository <Review, Long> {


    @Query(nativeQuery = true, value = "SELECT user_sys.id AS userId, user_sys.email AS username, review.review, review.rating FROM tb_review AS review INNER JOIN "
            + "tb_user AS user_sys ON review.user_id = user_sys.id INNER JOIN tb_movie AS movie ON review.movie_id = movie.id WHERE movie.id = :movieId")
         List<UserReviewProjection> searchReviewsByMovieId(@Param("movieId") Long movieId);

    @Query(nativeQuery = true, value = "SELECT review.review, review.rating, usuario.email AS username FROM tb_review AS review INNER JOIN tb_user AS usuario ON review.user_id = usuario.id WHERE review.id = :id")
     Optional<UserReviewProjection> searchReviewAndUserByReviewId(Long id);





}
