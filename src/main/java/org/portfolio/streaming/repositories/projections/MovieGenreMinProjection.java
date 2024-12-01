package org.portfolio.streaming.repositories.projections;

import java.time.Instant;

public interface MovieGenreMinProjection {

    Long getId();
    String getDescription();
    String getTitle();
    String getDirector();
    Instant getRelease();
    Double getPrice();
    String getImgUrl();
    String getGenres();
}
