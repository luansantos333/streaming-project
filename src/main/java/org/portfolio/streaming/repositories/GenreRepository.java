package org.portfolio.streaming.repositories;

import org.portfolio.streaming.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository <Genre, Long> {
}
