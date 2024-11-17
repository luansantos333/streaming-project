package org.portfolio.streaming.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "tb_review")
public class Review {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User userReview;
    @Column(columnDefinition = "TEXT")
    private String review;

    public Review(Long id, User userReview, String review) {
        this.id = id;
        this.userReview = userReview;
        this.review = review;
    }


    public Review(String review, Long id) {
        this.review = review;
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getUserReview() {
        return userReview;
    }

    public void setUserReview(User userReview) {
        this.userReview = userReview;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
