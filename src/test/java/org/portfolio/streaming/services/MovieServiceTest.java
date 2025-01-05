package org.portfolio.streaming.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.portfolio.streaming.config.TestConfig;
import org.portfolio.streaming.dtos.MovieGenreDTO;
import org.portfolio.streaming.dtos.MovieGenreMinDTO;
import org.portfolio.streaming.dtos.MovieGenreReviewDTO;
import org.portfolio.streaming.entities.Genre;
import org.portfolio.streaming.entities.Movie;
import org.portfolio.streaming.factories.MovieFactory;
import org.portfolio.streaming.repositories.MovieRepository;
import org.portfolio.streaming.repositories.ReviewRepository;
import org.portfolio.streaming.repositories.projections.MovieGenreMinProjection;
import org.portfolio.streaming.repositories.projections.MovieGenreProjection;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.portfolio.streaming.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ReviewRepository reviewRepository;
    private MovieGenreDTO movieGenreDTO;
    private Movie nullMovie;
    private Long validMovieId;
    private Long invalidMovieId;
    private Movie movie;
    private Genre genre;
    private List<MovieGenreProjection> movieGenreProjections = new ArrayList<>();
    private List<MovieGenreMinProjection> movieGenreMinProjections = new ArrayList<>();
    private List<Long> validMovieIds = new ArrayList<>();
    private String nonExistentMovieTitle;
    private Pageable pageable;
    private List<MovieGenreMinProjection> allMoviesGenres = new ArrayList<>();

    @Autowired
    RandomStringGenerator randomStringGenerator;
    @BeforeEach
    public void setUp () {

        nonExistentMovieTitle = "THIS MOVIE DOES NOT EXISTS";
        validMovieId = 1L;
        invalidMovieId = 2L;
        validMovieIds = List.of(validMovieId);
        movieGenreProjections.add(MovieFactory.defaultMovieGenreProjection());
        allMoviesGenres.add(MovieFactory.defaultMoviGenreMinDTO());
        allMoviesGenres.add(MovieFactory.defaultMoviGenreMinDTO());
        allMoviesGenres.add(MovieFactory.defaultMoviGenreMinDTO());


        movieGenreMinProjections.add(MovieFactory.defaultMoviGenreMinDTO());
        movie = MovieFactory.defaultMovie();
        movieGenreDTO = MovieFactory.defaultMovieGenreDTO();
        nullMovie = null;
        genre = new Genre(1L, randomStringGenerator.generateRandomString(20L));
        pageable = PageRequest.of(0, movieGenreMinProjections.size());
        Mockito.when(movieRepository.save(ArgumentMatchers.any())).thenReturn(movie);
        Mockito.when(movieRepository.searchMovieAndCategoriesById(validMovieId)).thenReturn(movieGenreProjections);
        Mockito.when(movieRepository.searchMovieAndCategoriesById(invalidMovieId)).thenReturn(List.of());
        Mockito.when(movieRepository.searchMovieIdsByTitle(movieGenreDTO.getTitle(), movieGenreDTO.getGenres().stream().map(x -> x.getId()).collect(Collectors.toList()))).thenReturn(validMovieIds);
        Mockito.when(movieRepository.searchMovieIdsByTitle(nonExistentMovieTitle, List.of(invalidMovieId))).thenReturn(List.of());
        Mockito.when(movieRepository.searchAllMoviesAndGenres(pageable)).thenReturn(new PageImpl<>(allMoviesGenres, pageable, allMoviesGenres.size()));
        Mockito.when(movieRepository.searchAllMoviesAndGenresByMovieIds(movieGenreDTO.getGenres().
                stream().
                map(x -> x.getId()).
                collect(Collectors.toList()), pageable)
        ).thenReturn(new PageImpl<>(movieGenreMinProjections, pageable, movieGenreMinProjections.size()));


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
    public void whenGetMovieGenderReviewByMovieIdWithValidIdThenReturnSuccess () {


        MovieGenreReviewDTO movieGenderReviewByMovieId = movieService.getMovieGenderReviewByMovieId(validMovieId);
        Assertions.assertEquals(validMovieId, movieGenreProjections.get(0).getId());


    }

    @Test
    public void whenGetMovieGenderReviewByMovieIdWithInvalidIdThenReturnAException() {


        Assertions.assertThrows(ResourceNotFoundException.class, () -> {

            movieService.getMovieGenderReviewByMovieId(invalidMovieId);

        });

    }

    @Test
    public void whenExistingMovieTitleAndGenreIdsThenReturnMovieGenreMinDTO () {

        Page<MovieGenreMinDTO> pageMovieGenre = movieService.findAllPaged(movieGenreDTO.getTitle(), pageable, movieGenreDTO.getGenres()
                .stream().
                map(x -> x.getId())
                .collect(Collectors.toList()));

        List<MovieGenreMinDTO> listMovieGenre = pageMovieGenre.toList();

        Assertions.assertEquals(listMovieGenre.get(0).getId(), movieGenreMinProjections.get(0).getId());
        Assertions.assertEquals(listMovieGenre.get(0).getTitle(), (movieGenreMinProjections.get(0).getTitle()));
        Assertions.assertEquals(listMovieGenre.get(0).getDirector(), (movieGenreMinProjections.get(0).getDirector()));
        Assertions.assertEquals(listMovieGenre.get(0).getImgUrl(), (movieGenreMinProjections.get(0).getImgUrl()));
        Assertions.assertEquals(listMovieGenre.get(0).getPrice(), (movieGenreMinProjections.get(0).getPrice()));
        Assertions.assertEquals(listMovieGenre.get(0).getRelease(), LocalDate.ofInstant(movieGenreMinProjections.get(0).getRelease(), ZoneId.of("America/Sao_Paulo")));

    }

    @Test
    public void whenNonExistingMovieTitleAndGenreIdsThenReturnAllMovieAndGenres () {


        Page<MovieGenreMinDTO> page = movieService.findAllPaged(nonExistentMovieTitle, pageable, List.of(invalidMovieId));
        List<MovieGenreMinDTO> movieGenreMinDTOList = page.stream().toList();

        Assertions.assertEquals(movieGenreMinDTOList.size(), 3);
        Assertions.assertEquals(movieGenreMinDTOList.get(0).getTitle(), allMoviesGenres.get(0).getTitle());




    }




}
