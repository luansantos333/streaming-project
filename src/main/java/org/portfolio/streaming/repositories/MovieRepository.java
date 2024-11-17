package org.portfolio.streaming.repositories;

import org.portfolio.streaming.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository <Movie, Long> {
}
