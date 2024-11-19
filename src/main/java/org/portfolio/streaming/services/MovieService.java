package org.portfolio.streaming.services;

import org.portfolio.streaming.dtos.MovieGenreReviewDTO;
import org.portfolio.streaming.repositories.MovieRepository;
import org.portfolio.streaming.repositories.ReviewRepository;
import org.portfolio.streaming.repositories.projections.MovieGenreProjection;
import org.portfolio.streaming.repositories.projections.UserReviewProjection;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;


    public MovieService(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public MovieGenreReviewDTO getMovieGenderReviewByMovieId(Long id) {

        List<MovieGenreProjection> movieGenreProjection = movieRepository.searchMovieAndCategoriesById(id);

        if (movieGenreProjection.isEmpty()) {
            throw new ResourceNotFoundException("No movie with this id found");
        }

        List<UserReviewProjection> userReviewProjections = reviewRepository.searchReviewsByMovieId(movieGenreProjection.get(0).getId());

        return new MovieGenreReviewDTO(movieGenreProjection, userReviewProjections);

    }




}
