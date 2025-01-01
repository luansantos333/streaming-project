package org.portfolio.streaming.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.portfolio.streaming.config.TestConfig;
import org.portfolio.streaming.dtos.MovieDTO;
import org.portfolio.streaming.dtos.MovieGenreDTO;
import org.portfolio.streaming.dtos.MovieGenreReviewDTO;
import org.portfolio.streaming.entities.Movie;
import org.portfolio.streaming.entities.Review;
import org.portfolio.streaming.factories.MovieFactory;
import org.portfolio.streaming.services.MovieService;
import org.portfolio.streaming.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(MovieController.class)
@Import(TestConfig.class)

public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;
    @MockBean
    private MovieService movieService;
    @Autowired
    private MockMvc mockMvc;
    private Movie movie;
    private MovieDTO movieDTO;
    private Long validId;
    private MovieGenreDTO movieGenreDTO;
    private MovieGenreReviewDTO movieGenreReviewDTO;
    private static final String uri = "http://localhost:8080/";
    private List<Review> reviewList;
    private String adminToken;
    private String userToken;
    private String invalidToken;
    @Autowired
    private TokenUtil generateToken;

    @BeforeEach
    public void setUp() throws Exception {

        adminToken = generateToken.obtainAccessToken(mockMvc, "admin@gmail.com", "123456");

        movie = MovieFactory.defaultMovie();
        movieDTO = MovieFactory.defaultMovieDTO();
        movieGenreDTO = MovieFactory.defaultMovieGenreDTO();
        reviewList = new ArrayList<>();
        movieGenreReviewDTO = MovieFactory.defaultMovieGenreReviewDTO(movie, reviewList);
        validId = 1L;
        Mockito.when(movieService.getMovieGenderReviewByMovieId(validId)).thenReturn(movieGenreReviewDTO);


    }


    @Test
    public void whenFindMovieByIdWithValidMovieIdThenReturnSuccess() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get(uri + "{id}", validId).header("Authorization", adminToken).contentType(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200));


    }


}
