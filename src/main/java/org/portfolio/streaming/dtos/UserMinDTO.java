package org.portfolio.streaming.dtos;

import org.portfolio.streaming.entities.User;

public class UserMinDTO {

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public UserMinDTO(User entity) {


        name = entity.getName();
        email = entity.getUsername();

    }

    public UserMinDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
