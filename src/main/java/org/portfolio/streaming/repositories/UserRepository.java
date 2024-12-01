package org.portfolio.streaming.repositories;

import org.portfolio.streaming.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query
    public Optional<User> findByEmail(String email);


}
