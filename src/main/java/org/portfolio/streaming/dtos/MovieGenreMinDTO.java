package org.portfolio.streaming.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.portfolio.streaming.entities.Genre;
import org.portfolio.streaming.entities.Movie;
import org.portfolio.streaming.repositories.projections.MovieGenreProjection;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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
    private List<GenreDTO> genres = new ArrayList<>();


    public MovieGenreMinDTO(Long id, String title, String director, Double price, LocalDate release, String imgUrl, List<GenreDTO> genres) {
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

    public MovieGenreMinDTO(Movie entity) {
        id = entity.getId();
        title = entity.getTitle();
        director = entity.getDirector();
        price = entity.getPrice();
        release = entity.getRelease();
        for (Genre g : entity.getGenres()) {

            genres.add(new GenreDTO(g));

        }

    }


    public MovieGenreMinDTO (List<MovieGenreProjection> projection) {


        id = projection.get(0).getId();
        title = projection.get(0).getTitle();
        director = projection.get(0).getDirector();
        price = projection.get(0).getPrice();
        release = LocalDate.ofInstant(projection.get(0).getRelease(), ZoneId.of("America/Sao_Paulo"));
        imgUrl = projection.get(0).getImgUrl();
        for (MovieGenreProjection p : projection) {

            genres.add(new GenreDTO(p.getId(), p.getGenreName()));

        }

    }


    public MovieGenreMinDTO (MovieGenreProjection projection) {

        id = projection.getId();
        title = projection.getTitle();
        director = projection.getDirector();
        price = projection.getPrice();
        release = LocalDate.ofInstant(projection.getRelease(), ZoneId.of("America/Sao_Paulo"));
        imgUrl = projection.getImgUrl();
        genres.add(new GenreDTO(projection.getId(), projection.getGenreName()));


    }

    public List<GenreDTO> getGenres() {
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