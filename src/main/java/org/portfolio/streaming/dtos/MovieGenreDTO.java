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

public class MovieGenreDTO {
    private Long id;
    @Size (max = 255, message = "The maximum length for the title is 255 characters")
    @NotBlank (message = "You can't add a movie without a tittle")
    private String title;
    @NotBlank (message = "You can't add a movie without a director")
    private String director;
    @Size (max = 1000, message = "The maximum number of characters the description can have is 1000 characters")
    @NotBlank (message = "You can't add a movie without a description")
    private String description;
    @Positive (message = "The price need to be greater than zero")
    private Double price;
    private LocalDate release;
    @NotBlank (message = "You can't add a movie without linking a image to it")
    private String imgUrl;
    private List<GenreDTO> genres = new ArrayList<>();


    public MovieGenreDTO(Long id, String title, String director, String description, Double price, LocalDate release, String imgUrl, List<GenreDTO> genres) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.price = price;
        this.release = release;
        this.imgUrl = imgUrl;
        this.genres = genres;
    }

    public MovieGenreDTO(Long id, String title, String director, String description, Double price, LocalDate release) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.price = price;
        this.release = release;
    }


    public MovieGenreDTO() {
    }

    public MovieGenreDTO(Movie entity) {
        id = entity.getId();
        title = entity.getTitle();
        director = entity.getDirector();
        description = entity.getDescription();
        price = entity.getPrice();
        release = entity.getRelease();
        for (Genre g : entity.getGenres()) {

            genres.add(new GenreDTO(g));

        }

    }


    public MovieGenreDTO (List<MovieGenreProjection> projection) {


        id = projection.get(0).getId();
        title = projection.get(0).getTitle();
        director = projection.get(0).getDirector();
        description = projection.get(0).getDescription();
        price = projection.get(0).getPrice();
        release = LocalDate.ofInstant(projection.get(0).getRelease(), ZoneId.of("America/Sao_Paulo"));
        imgUrl = projection.get(0).getImgUrl();
        for (MovieGenreProjection p : projection) {

            genres.add(new GenreDTO(p.getId(), p.getGenreName()));

        }

    }


    public MovieGenreDTO (MovieGenreProjection projection) {

        id = projection.getId();
        title = projection.getTitle();
        director = projection.getDirector();
        description = projection.getDescription();
        price = projection.getPrice();
        release = LocalDate.ofInstant(projection.getRelease(), ZoneId.of("America/Sao_Paulo"));
        imgUrl = projection.getImgUrl();
        genres.add(new GenreDTO(projection.getId(), projection.getGenreName()));


    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
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
