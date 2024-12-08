package org.portfolio.streaming.repositories;

import org.portfolio.streaming.entities.Movie;
import org.portfolio.streaming.repositories.projections.MovieGenreMinProjection;
import org.portfolio.streaming.repositories.projections.MovieGenreProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository <Movie, Long> {


    @Query(nativeQuery = true, value = "SELECT movie.id, movie.title, movie.description, movie.director, movie.release, movie.price, movie.img_url AS imgUrl, genre.id AS genre_id, genre.name AS genre_name " +
            "FROM tb_movie AS movie INNER JOIN tb_movie_genre ON movie.id = tb_movie_genre.movie_id INNER JOIN tb_genre AS genre ON genre.id = tb_movie_genre.genre_id WHERE movie.id = :id"
    )
    List<MovieGenreProjection> searchMovieAndCategoriesById(Long id);

    @Query ("SELECT movie.id FROM Movie movie WHERE UPPER(movie.title) LIKE UPPER (CONCAT('%', :name, '%'))")
    List<Long> searchMovieIdsByTitle(String name);


    @Query(nativeQuery = true, value = "SELECT * FROM (SELECT movie.id, movie.title, movie.release, movie.director, movie.img_url AS imgUrl, movie.price, STRING_AGG (genre.name,', ') AS genres " +
            "FROM tb_movie AS movie INNER JOIN tb_movie_genre AS movie_genre ON movie.id = movie_genre.movie_id INNER JOIN tb_genre AS genre ON genre.id = movie_genre.genre_id WHERE movie.id IN (:movieIds) GROUP BY movie.id, movie.title, movie.release, movie.director, movie.img_url, movie.price) AS selection", countQuery = "SELECT COUNT (*) FROM (SELECT movie.id, movie.title, movie.release, movie.director, movie.img_url AS imgUrl, movie.price, STRING_AGG (genre.name,', ') AS genres" +
            " FROM tb_movie AS movie INNER JOIN tb_movie_genre AS movie_genre ON movie.id = movie_genre.movie_id INNER JOIN tb_genre AS genre ON genre.id = movie_genre.genre_id WHERE movie.id IN (:movieIds) GROUP BY movie.id, movie.title, movie.release, movie.director, movie.img_url, movie.price) AS selection")
    Page<MovieGenreMinProjection> searchAllMoviesAndGenresByMovieIds(List<Long> movieIds, Pageable p);


    @Query(nativeQuery = true, value = "SELECT * FROM (SELECT movie.id, movie.title, movie.release, movie.director, movie.img_url AS imgUrl, movie.price, STRING_AGG (genre.name,', ') AS genres " +
            "FROM tb_movie AS movie INNER JOIN tb_movie_genre AS movie_genre ON movie.id = movie_genre.movie_id INNER JOIN tb_genre AS genre ON genre.id = movie_genre.genre_id GROUP BY movie.id, movie.title, movie.release, movie.director, movie.img_url, movie.price) AS selection", countQuery = "SELECT COUNT (*) FROM (SELECT movie.id, movie.title, movie.release, movie.director, movie.img_url AS imgUrl, movie.price, STRING_AGG (genre.name,', ') AS genres" +
            " FROM tb_movie AS movie INNER JOIN tb_movie_genre AS movie_genre ON movie.id = movie_genre.movie_id INNER JOIN tb_genre AS genre ON genre.id = movie_genre.genre_id GROUP BY movie.id, movie.title, movie.release, movie.director, movie.img_url, movie.price) AS selection")
    Page<MovieGenreMinProjection> searchAllMoviesAndGenres(Pageable p);



}
