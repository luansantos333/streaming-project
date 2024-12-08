package org.portfolio.streaming.controllers;

import org.portfolio.streaming.dtos.UserReviewDTO;
import org.portfolio.streaming.dtos.UserReviewMinDTO;
import org.portfolio.streaming.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping ("/review")
public class ReviewController {

    @Autowired
    ReviewService service;



    @PostMapping
    @PreAuthorize("hasAnyRole ('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<UserReviewDTO> newReview (@RequestBody UserReviewDTO dto) {

        UserReviewDTO userReviewDTO = service.newReview(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getUserId()).toUri();

        return ResponseEntity.created(uri).body(userReviewDTO);
    }



    @PreAuthorize("hasAnyRole ('ROLE_ADMIN','ROLE_USER')")
    @GetMapping ("/{id}")
    public ResponseEntity<UserReviewMinDTO> getReviewById (@PathVariable (name = "id") Long id) {

        UserReviewMinDTO byReviewId = service.findByReviewId(id);
        return ResponseEntity.ok(byReviewId);

    }

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping ("/{id}")
    public void deleteReviewById (@PathVariable Long id) {

        service.deleteReviewById(id);

    }

    @PreAuthorize("hasRole ('ROLE_USER')")
    @PutMapping ("/{id}")
    public ResponseEntity<UserReviewDTO> updateReviewById (@PathVariable Long id, @RequestBody UserReviewDTO dto) {
        UserReviewDTO userReviewDTO = service.updateReview(dto, id);
        return ResponseEntity.ok(userReviewDTO);

    }



}
