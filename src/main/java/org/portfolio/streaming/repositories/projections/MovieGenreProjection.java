package org.portfolio.streaming.repositories.projections;

import java.time.Instant;

public interface MovieGenreProjection {
    Long getId();

    String getDescription();

    String getTitle();

    String getDirector();

    Instant getRelease();

    Double getPrice();
    Long getGenreId();
    String getImgUrl();
    String getGenreName();
}
