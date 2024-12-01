package org.portfolio.streaming.repositories.projections;

import java.time.Instant;
import java.util.List;

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
