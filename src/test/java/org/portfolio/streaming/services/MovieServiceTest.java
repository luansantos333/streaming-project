package org.portfolio.streaming.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.portfolio.streaming.dtos.MovieGenreDTO;
import org.portfolio.streaming.entities.Movie;
import org.portfolio.streaming.factories.MovieFactory;
import org.portfolio.streaming.repositories.MovieRepository;
import org.portfolio.streaming.repositories.ReviewRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ReviewRepository reviewRepository;
    private MovieGenreDTO movieGenreDTO;
    private Long validMovieId;
    private Long invalidMovieId;
    private Movie movie;
    @BeforeEach
    public void setUp () {

        validMovieId = 1L;
        invalidMovieId = 2L;
        movie = MovieFactory.defaultMovie();
        movieGenreDTO = MovieFactory.defaultMovieGenreDTO();
        Mockito.when(movieRepository.save(ArgumentMatchers.any())).thenReturn(movie);

    }


    @Test
    public void whenMovieGenreDTOIsValidThenReturnSuccess () {

        MovieGenreDTO dto = movieService.addNewMovie(movieGenreDTO);
        Assertions.assertEquals(dto.getId(), validMovieId);
        Assertions.assertEquals(dto.getTitle(), movieGenreDTO.getTitle());
        Assertions.assertEquals(dto.getDescription(), movieGenreDTO.getDescription());
        Assertions.assertEquals(dto.getDirector(), movieGenreDTO.getDirector());
        Assertions.assertEquals(dto.getPrice(), movieGenreDTO.getPrice());
        Assertions.assertEquals(dto.getImgUrl(), movieGenreDTO.getImgUrl());
        Assertions.assertEquals(dto.getRelease(), movieGenreDTO.getRelease());
        Assertions.assertEquals(dto.getGenres().get(0).getName(), movieGenreDTO.getGenres().get(0).getName());
        Assertions.assertEquals(dto.getGenres().get(0).getId(), movieGenreDTO.getGenres().get(0).getId());

    }

    @Test
    public void whenMovieTittleIsLongerThan255CharactersThrowException () {



    }




}
