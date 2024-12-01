package org.portfolio.streaming.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.portfolio.streaming.repositories.projections.MovieGenreMinProjection;

import java.time.LocalDate;
import java.time.ZoneId;

public class MovieGenreMinDTO {

    private Long id;
    @Size(max = 255, message = "The maximum length for the title is 255 characters")
    @NotBlank(message = "You can't add a movie without a tittle")
    private String title;
    @NotBlank (message = "You can't add a movie without a director")
    private String director;
    @Positive(message = "The price need to be greater than zero")
    private Double price;
    private LocalDate release;
    @NotBlank (message = "You can't add a movie without linking a image to it")
    private String imgUrl;
    private GenreMinDTO genres;


    public MovieGenreMinDTO(Long id, String title, String director, Double price, LocalDate release, String imgUrl, GenreMinDTO genres) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.price = price;
        this.release = release;
        this.imgUrl = imgUrl;
        this.genres = genres;
    }

    public MovieGenreMinDTO(Long id, String title, String director, Double price, LocalDate release) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.price = price;
        this.release = release;
    }


    public MovieGenreMinDTO() {
    }



    public MovieGenreMinDTO (MovieGenreMinProjection projection) {

        id = projection.getId();
        title = projection.getTitle();
        director = projection.getDirector();
        price = projection.getPrice();
        release = LocalDate.ofInstant(projection.getRelease(), ZoneId.of("America/Sao_Paulo"));
        imgUrl = projection.getImgUrl();
        genres = new GenreMinDTO(projection.getGenres());


    }


    public GenreMinDTO getGenres() {
        return genres;
    }

    public String getDirector() {
        return director;
    }

    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getRelease() {
        return release;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
