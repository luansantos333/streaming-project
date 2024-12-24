package org.portfolio.streaming.repositories;

import org.portfolio.streaming.entities.PasswordReset;
import org.portfolio.streaming.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository <PasswordReset, Long> {

    @Query ("SELECT r.user FROM org.portfolio.streaming.entities.PasswordReset r WHERE r.token = :token")
    Optional<User> searchUserByResetToken(String token);

    @Query
    PasswordReset findByToken(String token);


}
