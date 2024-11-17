package org.portfolio.streaming.repositories;

import org.portfolio.streaming.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
