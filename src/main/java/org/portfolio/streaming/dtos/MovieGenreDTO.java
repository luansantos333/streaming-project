package org.portfolio.streaming.dtos;

import org.portfolio.streaming.entities.Genre;
import org.portfolio.streaming.entities.Movie;
import org.portfolio.streaming.repositories.projections.MovieGenreProjection;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MovieGenreDTO {
    private Long id;
    private String title;
    private String director;
    private String description;
    private Double price;
    private LocalDate release;
    private List<GenreDTO> genres = new ArrayList<>();


    public MovieGenreDTO(String description, String director, List<GenreDTO> genres, Long id, Double price, LocalDate release, String title) {
        this.description = description;
        this.director = director;
        this.genres = genres;
        this.id = id;
        this.price = price;
        this.release = release;
        this.title = title;
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
        for (MovieGenreProjection p : projection) {

            genres.add(new GenreDTO(p.getId(), p.getGenreName()));

        }

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
}
