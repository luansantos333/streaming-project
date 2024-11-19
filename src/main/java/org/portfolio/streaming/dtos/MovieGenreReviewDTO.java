package org.portfolio.streaming.dtos;

import org.portfolio.streaming.repositories.projections.MovieGenreProjection;
import org.portfolio.streaming.repositories.projections.UserReviewProjection;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MovieGenreReviewDTO {

    private Long id;
    private String title;
    private String director;
    private String description;
    private Double price;
    private LocalDate release;
    private String imgUrl;
    private List<GenreDTO> genres = new ArrayList<>();
    private List<UserReviewDTO> reviews = new ArrayList<>();


    public MovieGenreReviewDTO(Long id, String title, String director, String description, Double price, LocalDate release, String imgUrl, List<GenreDTO> genres, List<UserReviewDTO> reviews) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.price = price;
        this.release = release;
        this.imgUrl = imgUrl;
        this.genres = genres;
        this.reviews = reviews;
    }


    public MovieGenreReviewDTO(List<MovieGenreProjection> movieGender, List<UserReviewProjection> reviews) {


        id = movieGender.get(0).getId();
        title = movieGender.get(0).getTitle();
        director = movieGender.get(0).getDirector();
        description = movieGender.get(0).getDescription();
        price = movieGender.get(0).getPrice();
        release = LocalDate.ofInstant(movieGender.get(0).getRelease(), ZoneId.of("America/Sao_Paulo"));
        imgUrl = movieGender.get(0).getImgUrl();
        for (MovieGenreProjection p : movieGender) {

            genres.add(new GenreDTO(p.getId(), p.getGenreName()));

        }

        for (UserReviewProjection r : reviews) {

            getReviews().add(new UserReviewDTO(r.getUsername(), r.getReview(), r.getRating(), r.getUserId()));

        }


    }



    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getRelease() {
        return release;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public List<UserReviewDTO> getReviews() {
        return reviews;
    }
}
