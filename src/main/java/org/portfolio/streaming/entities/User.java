package org.portfolio.streaming.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table (name = "tb_user")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column (unique = true)
    private String email;
    private String password;
    @OneToMany (mappedBy = "userReview")
    private List<Review> reviews;
    @ManyToMany
    @JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<>();

    public User(Long id, String name, String email, String password, List<Review> reviews, Set<Role> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.reviews = reviews;
        this.authorities = authorities;
    }

    public User() {
    }

    public User(Long id, String name, String email, String password, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.reviews = reviews;
    }


    public List<Review> getReviews() {
        return reviews;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
