package org.portfolio.streaming.services;

import org.portfolio.streaming.dtos.MovieGenreDTO;
import org.portfolio.streaming.repositories.MovieRepository;
import org.portfolio.streaming.repositories.projections.MovieGenreProjection;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;


    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }



    @Transactional
    public MovieGenreDTO getProductById (Long id) {

        List<MovieGenreProjection> movieGenreProjection = movieRepository.searchMovieAndCategoriesById(id);

        if (movieGenreProjection.isEmpty()) {
            throw new ResourceNotFoundException("No movie with this id found");
        }

        return new MovieGenreDTO(movieGenreProjection);

    }




}
