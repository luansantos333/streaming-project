package org.portfolio.streaming.services;

import jakarta.persistence.EntityNotFoundException;
import org.portfolio.streaming.dtos.GenreDTO;
import org.portfolio.streaming.dtos.MovieGenreDTO;
import org.portfolio.streaming.dtos.MovieGenreMinDTO;
import org.portfolio.streaming.dtos.MovieGenreReviewDTO;
import org.portfolio.streaming.entities.Genre;
import org.portfolio.streaming.entities.Movie;
import org.portfolio.streaming.repositories.MovieRepository;
import org.portfolio.streaming.repositories.ReviewRepository;
import org.portfolio.streaming.repositories.projections.MovieGenreMinProjection;
import org.portfolio.streaming.repositories.projections.MovieGenreProjection;
import org.portfolio.streaming.repositories.projections.UserReviewProjection;
import org.portfolio.streaming.services.exceptions.DatabaseException;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional (readOnly = true)
    public MovieGenreReviewDTO getMovieGenderReviewByMovieId(Long id) {

        List<MovieGenreProjection> movieGenreProjection = movieRepository.searchMovieAndCategoriesById(id);

        if (movieGenreProjection.isEmpty()) {
            throw new ResourceNotFoundException("No movie with this id found");
        }

        List<UserReviewProjection> userReviewProjections = reviewRepository.searchReviewsByMovieId(movieGenreProjection.get(0).getId());

        return new MovieGenreReviewDTO(movieGenreProjection, userReviewProjections);

    }

    @Transactional (readOnly = true)
    public Page<MovieGenreMinDTO> findAllPaged (String name, Pageable p, List<Long> genreIds) {


        List<Long> movieIds = movieRepository.searchMovieIdsByTitle(name, genreIds);

        Page<MovieGenreMinProjection> movieGenreProjections;

        if (movieIds.isEmpty()) {

            movieGenreProjections = movieRepository.searchAllMoviesAndGenres(p);

            return movieGenreProjections.map(x -> new MovieGenreMinDTO(x));

        }

        movieGenreProjections = movieRepository.searchAllMoviesAndGenresByMovieIds(movieIds, p);


        return movieGenreProjections.map(x -> new MovieGenreMinDTO(x));
    }


    @Transactional
    public MovieGenreDTO addNewMovie (MovieGenreDTO movieGenreDTO)  {

        Movie movie = new Movie();
        copyDTOToEntity(movieGenreDTO, movie);
        Movie movieSaved  = movieRepository.save(movie);
        return new MovieGenreDTO(movieSaved);

    }


    @Transactional
    public MovieGenreDTO updateMovie (MovieGenreDTO movieGenreDTO, Long id) {

        try {
            Movie movie = movieRepository.getReferenceById(id);
            copyDTOToEntity(movieGenreDTO, movie);
            movie = movieRepository.save(movie);
            return new MovieGenreDTO(movie);
        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException("We couldn't found a movie with this id");

        }


    }

    @Transactional
    public void deleteMovieById (Long id) {

        if (!movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("The movie you are trying to delete does not exists or was already removed");
        }

        try {
            movieRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {

            throw new DatabaseException("Couldn't delete movie because it has dependencies with other entities");

        }

    }



    private void copyDTOToEntity (MovieGenreDTO dto, Movie entity) {


        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDirector(dto.getDirector());
        entity.setPrice(dto.getPrice());
        entity.setRelease(dto.getRelease());
        entity.setImgUrl(dto.getImgUrl());

        for (GenreDTO d : dto.getGenres()) {


            entity.getGenres().add(new Genre(d.getId(), d.getName()));

        }


    }


}
