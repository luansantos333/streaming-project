package org.portfolio.streaming.controllers;


import org.portfolio.streaming.dtos.MovieGenreDTO;
import org.portfolio.streaming.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;


    @GetMapping ("/{id}")
    public ResponseEntity<MovieGenreDTO> findMovieAndGenresByMovieId (@PathVariable (name = "id") Long id) {

        HttpStatus status = HttpStatus.ACCEPTED;

        return ResponseEntity.status(status).body(movieService.getProductById(id));

    }




}
