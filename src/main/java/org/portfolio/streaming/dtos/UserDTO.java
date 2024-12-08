package org.portfolio.streaming.dtos;

import org.portfolio.streaming.entities.User;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;

    public UserDTO(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserDTO(User entity) {

        id = entity.getId();
        name = entity.getName();
        email = entity.getUsername();
        password = entity.getPassword();


    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
