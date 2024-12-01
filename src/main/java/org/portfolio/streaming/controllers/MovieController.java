package org.portfolio.streaming.controllers;


import jakarta.validation.Valid;
import org.portfolio.streaming.dtos.MovieGenreDTO;
import org.portfolio.streaming.dtos.MovieGenreMinDTO;
import org.portfolio.streaming.dtos.MovieGenreReviewDTO;
import org.portfolio.streaming.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping ("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;



    @GetMapping
    public ResponseEntity<Page<MovieGenreMinDTO>> findAllMovies (@RequestParam (name = "name", defaultValue = "a") String name, Pageable p) {


        Page<MovieGenreMinDTO> allPaged = movieService.findAllPaged(name, p);
        return ResponseEntity.ok(allPaged);

    }


    @GetMapping ("/{id}")
    public ResponseEntity<MovieGenreReviewDTO> findMovieAndGenresByMovieId (@PathVariable (name = "id")  Long id) {

        HttpStatus status = HttpStatus.ACCEPTED;

        return ResponseEntity.status(status).body(movieService.getMovieGenderReviewByMovieId(id));

    }

    @PostMapping
    public ResponseEntity<MovieGenreDTO> addNewMovie (@Valid @RequestBody  MovieGenreDTO dto) {

        MovieGenreDTO movieGenreDTO = movieService.addNewMovie(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(movieGenreDTO);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<MovieGenreDTO> updateMovieInfo (@Valid @RequestBody MovieGenreDTO dto, @PathVariable (name = "id") Long id) {

        MovieGenreDTO movieGenreDTO = movieService.updateMovie(dto, id);
        return ResponseEntity.ok(movieGenreDTO);

    }

    @DeleteMapping ("{id}")
    public void deleteMovieById (@PathVariable (name = "id") Long id) {

        movieService.deleteMovieById(id);
        ResponseEntity.noContent();

    }








}
