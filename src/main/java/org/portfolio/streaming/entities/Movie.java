package org.portfolio.streaming.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table (name = "tb_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private String description;
    private Double price;
    @Column (columnDefinition = "TIMESTAMP WHITEOUT TIMEZONE")
    private Instant launchmentDate;
    @ManyToMany
    @JoinTable (name = "tb_movie_genre", joinColumns = @JoinColumn (name = "movie_id"), inverseJoinColumns = @JoinColumn (name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    public Movie(Long id, String title, String director, String description, Double price, Instant launchmentDate, Set<Genre> genres) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.price = price;
        this.launchmentDate = launchmentDate;
        this.genres = genres;
    }

    public Movie() {
    }

    public Movie(Long id, String title, String director, String description, Double price, Instant launchmentDate) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.price = price;
        this.launchmentDate = launchmentDate;
    }

    public Set<Genre> getGenres() {
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

    public Instant getLaunchmentDate() {
        return launchmentDate;
    }

    public void setLaunchmentDate(Instant launchmentDate) {
        this.launchmentDate = launchmentDate;
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
