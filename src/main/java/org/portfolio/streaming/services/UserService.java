package org.portfolio.streaming.services;

import org.portfolio.streaming.dtos.UserDTO;
import org.portfolio.streaming.entities.User;
import org.portfolio.streaming.repositories.UserRepository;
import org.portfolio.streaming.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional (readOnly = true)
    public UserDTO findByEmail (String email) {
        User user = repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException ("No user found with this email"));
        return new UserDTO(user);
    }


}
