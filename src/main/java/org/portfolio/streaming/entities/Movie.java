package org.portfolio.streaming.entities;

import jakarta.persistence.*;
import org.portfolio.streaming.dtos.MovieDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "tb_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    @Column (columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String imgUrl;
    @Column (columnDefinition = "TIMESTAMP")
    private LocalDate release;
    @ManyToMany
    @JoinTable (name = "tb_movie_genre", joinColumns = @JoinColumn (name = "movie_id"), inverseJoinColumns = @JoinColumn (name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();
    @OneToMany (mappedBy = "movie")
    private List<Review> userRatings = new ArrayList<>();


    public Movie(String description, String director, List<Genre> genres, Long id, Double price, LocalDate release, String title, List<Review> userRatings) {
        this.description = description;
        this.director = director;
        this.genres = genres;
        this.id = id;
        this.price = price;
        this.release = release;
        this.title = title;
        this.userRatings = userRatings;
    }

    public Movie() {
    }

    public Movie(Long id, String title, String director, Double price, LocalDate release, String imgUrl) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.price = price;
        this.release = release;
        this.imgUrl = imgUrl;
    }

    public Movie (MovieDTO dto, List<Genre> genres) {


        id = dto.getId();
        title = dto.getTitle();
        director = dto.getDirector();
        price = dto.getPrice();
        release = dto.getRelease();
        imgUrl = dto.getImgUrl();

        for (Genre genre : genres) {

            getGenres().add(genre);

        }


    }


    public List<Review> getUserRatings() {
        return userRatings;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }


    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
