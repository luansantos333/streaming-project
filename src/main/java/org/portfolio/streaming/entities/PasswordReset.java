package org.portfolio.streaming.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity (name = "tb_pass_reset_token")
public class PasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne (targetEntity = User.class)
    @JoinColumn (name = "user_id")
    private User user;
    private LocalDateTime expiryDate;


    public PasswordReset() {
    }

    public PasswordReset(String token) {
        this.token = token;
    }


    public PasswordReset(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
