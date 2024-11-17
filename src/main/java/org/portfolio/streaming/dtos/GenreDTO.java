package org.portfolio.streaming.dtos;

import org.portfolio.streaming.entities.Genre;

public class GenreDTO {

    private Long id;
    private String name;


    public GenreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public GenreDTO(Genre genre) {

        id = genre.getId();
        name = getName();

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
