package org.portfolio.streaming.repositories;

import org.portfolio.streaming.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository <Review, Long> {
}
