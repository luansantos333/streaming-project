package org.portfolio.streaming.controllers;

import org.portfolio.streaming.dtos.UserDTO;
import org.portfolio.streaming.dtos.UserMinDTO;
import org.portfolio.streaming.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @PostMapping
    public ResponseEntity<UserMinDTO> addNewUser (@RequestBody UserDTO dto) {

        UserMinDTO userMinDTO = service.addNewUser(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(userMinDTO);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping ("/{id}")
    public ResponseEntity<UserMinDTO> elevateUserPrivileges (@PathVariable (name = "id") Long id) {

        UserMinDTO user = service.grantAdminPrivileges(id);

        return ResponseEntity.ok(user);
    }


    @DeleteMapping ("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> deleteUser (@PathVariable (name = "id") Long id) {

        service.deleteUser(id);

        return ResponseEntity.noContent().build();

    }




}
