package org.portfolio.streaming.controllers;

import org.portfolio.streaming.dtos.UserMinDTO;
import org.portfolio.streaming.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/user")
public class UserController {


    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<UserMinDTO> getMe () {


        UserMinDTO me = service.getMe();

        return ResponseEntity.ok(me);

    }

    /*
    @PostMapping
    public ResponseEntity<UserMinDTO> addNewUser () {



    }
    */

    @DeleteMapping ("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> deleteUser (@PathVariable (name = "id") Long id) {

        service.deleteUser(id);

        return ResponseEntity.noContent().build();

    }




}
