package org.portfolio.streaming.controllers;

import org.portfolio.streaming.services.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/password/token")
public class PasswordResetController {

    private final PasswordResetService service;


    public PasswordResetController(PasswordResetService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> requestPasswordResetToken (@RequestParam (required = true, name = "email") String email) {
        service.initiatePasswordReset(email);
        return ResponseEntity.noContent().build();

    }

    @PostMapping ("/reset")
    public ResponseEntity<Void> resetPassword (@RequestParam (required = true, name = "token") String token, @RequestParam (required = true, name = "password") String password) {

        service.resetPassword(token, password);

        return ResponseEntity.noContent().build();

    }




}
