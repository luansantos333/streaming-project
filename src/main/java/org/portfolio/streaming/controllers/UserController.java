package org.portfolio.streaming.controllers;

import org.portfolio.streaming.dtos.UserMinDTO;
import org.portfolio.streaming.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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





}
