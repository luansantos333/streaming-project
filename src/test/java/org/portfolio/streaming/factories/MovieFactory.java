package org.portfolio.streaming.factories;

import org.portfolio.streaming.dtos.MovieDTO;
import org.portfolio.streaming.dtos.MovieGenreDTO;
import org.portfolio.streaming.dtos.MovieGenreReviewDTO;
import org.portfolio.streaming.entities.*;
import org.portfolio.streaming.repositories.projections.MovieGenreMinProjection;
import org.portfolio.streaming.repositories.projections.MovieGenreProjection;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieFactory {

    public static MovieDTO defaultMovieDTO () {

        return new MovieDTO(1L, "SOME MOVIE", "SOME DIRECTOR", 50.0, "https://someimage.com", LocalDate.now());

    }

    public static MovieDTO customMovieDTO (Long id, String title, String director, Double price, String imgUrl, LocalDate release) {

        return new MovieDTO(id, title, director, price, imgUrl, release);

    }

    public static Movie defaultMovie () {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1L, "SOME GENRE"));
        return new Movie(defaultMovieDTO(), genres);
    }

    public static Movie customMovie (Long id, String title, String director, Double price, String imgUrl, LocalDate release, List<Genre> genres) {


        Movie movie = new Movie(id, title, director, price, LocalDate.now(), imgUrl);
        for (Genre genre : genres) {


            movie.getGenres().add(genre);


        }

        return movie;

    }


    public static MovieGenreDTO defaultMovieGenreDTO () {

        return new MovieGenreDTO(defaultMovie());

    }

    public static MovieGenreDTO customMovieGenreDTO (Long id, String title, String director, Double price, String imgUrl, LocalDate release, List<Genre> genres) {

        return new MovieGenreDTO(customMovie(id, title, director, price, imgUrl,  release, genres));


    }

    public static MovieGenreReviewDTO defaultMovieGenreReviewDTO (Movie movie, List<Review> review) {

        review = new ArrayList<>();

        review.add(new Review("SOME REVIEW", 1L, 8.5));
        review.add(new Review("ANOTHER REVIEW", 2L, 3.5));
        review.get(0).setUserReview(new User(1L, "SOME USER", "test@test.com", "1234567"));
        review.get(0).getUserReview().addRole(new Role("ROLE_USER", 1L));
        review.get(1).setUserReview(new User(2L, "ANOTHER USER", "test2@test.com", "1234567"));
        review.get(0).getUserReview().addRole(new Role("ROLE_USER", 1L));

        return new MovieGenreReviewDTO(defaultMovie(), review);

    }


    public static MovieGenreimpl defaultMovieGenreProjection() {

        return new MovieGenreimpl();

    }

    public static MovieGenreMinProjection defaultMoviGenreMinDTO () {

        return new MovieGenreMinImpl();

    }





    private static class MovieGenreimpl implements MovieGenreProjection {




        @Override
        public Long getId() {
            return 1L;
        }

        @Override
        public String getDescription() {
            return "SOME TEXT";
        }

        @Override
        public String getTitle() {
            return "TITLE";
        }

        @Override
        public String getDirector() {
            return "DIRECTOR";
        }

        @Override
        public Instant getRelease() {
            return Instant.now();
        }

        @Override
        public Double getPrice() {
            return 1.50;
        }

        @Override
        public Long getGenreId() {
            return 1L;
        }

        @Override
        public String getImgUrl() {
            return "SOME IMAGE";
        }

        @Override
        public String getGenreName() {
            return "SOME GENRE";
        }
    }

    private static class MovieGenreMinImpl implements MovieGenreMinProjection {


        @Override
        public Long getId() {
            return 1L;
        }

        @Override
        public String getDescription() {
            return "DESCRIPTION TEST";
        }

        @Override
        public String getTitle() {
            return "TITLE TEST";
        }

        @Override
        public String getDirector() {
            return "DIRECTOR TEST";
        }

        @Override
        public Instant getRelease() {
            return Instant.ofEpochMilli(1736046000000L);
        }

        @Override
        public Double getPrice() {
            return 10.0;
        }

        @Override
        public String getImgUrl() {
            return "SOME IMAGE";
        }

        @Override
        public String getGenres() {
            return "SOMO GENRE";
        }
    }



}
