package org.portfolio.streaming.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "tb_role")
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany (mappedBy = "authorities")
    private String authority;

    public Role(String authority, Long id) {
        this.authority = authority;
        this.id = id;
    }


    public Role() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}