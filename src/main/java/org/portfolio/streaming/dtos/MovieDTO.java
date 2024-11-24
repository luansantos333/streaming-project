package org.portfolio.streaming.dtos;

import org.portfolio.streaming.entities.Movie;

import java.time.LocalDate;

public class MovieDTO {

    private Long id;
    private String title;
    private String director;
    private Double price;
    private String imgUrl;
    private LocalDate release;


    public MovieDTO(Long id, String title, String director, Double price, String imgUrl, LocalDate release) {
        this.id = id;
        this.title = title;
        this.director = director;

        this.price = price;
        this.imgUrl = imgUrl;
        this.release = release;
    }


    public MovieDTO (Movie entity) {

        this.id = entity.getId();
        this.title = entity.getTitle();
        this.director = entity.getDirector();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.release = entity.getRelease();

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

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public LocalDate getRelease() {
        return release;
    }
}
