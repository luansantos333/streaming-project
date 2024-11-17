package org.portfolio.streaming.repositories;

import org.portfolio.streaming.entities.Movie;
import org.portfolio.streaming.repositories.projections.MovieGenreProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository <Movie, Long> {


    @Query(nativeQuery = true, value = "SELECT movie.id, movie.title, movie.description, movie.director, movie.release, movie.price, genre.id AS genre_id, genre.name AS genre_name " +
            "FROM tb_movie AS movie INNER JOIN tb_movie_genre ON movie.id = tb_movie_genre.movie_id INNER JOIN tb_genre AS genre ON genre.id = tb_movie_genre.genre_id WHERE movie.id = :id"
    )
    List<MovieGenreProjection> searchMovieAndCategoriesById(Long id);


}
