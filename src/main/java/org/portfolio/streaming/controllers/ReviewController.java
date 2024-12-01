package org.portfolio.streaming.controllers;

import org.portfolio.streaming.dtos.UserReviewMinDTO;
import org.portfolio.streaming.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/review")
public class ReviewController {

    @Autowired
    ReviewService service;

    /*

    @PostMapping
    public ResponseEntity<UserReviewDTO> newReview (@RequestBody UserReviewDTO dto) {

        UserReviewDTO userReviewDTO = service.newReview(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getUserId()).toUri();

        return ResponseEntity.created(uri).body(userReviewDTO);


    }
    */


    @GetMapping ("/{id}")
    public ResponseEntity<UserReviewMinDTO> getReviewById (@PathVariable (name = "id") Long id) {

        UserReviewMinDTO byReviewId = service.findByReviewId(id);
        return ResponseEntity.ok(byReviewId);

    }



}
