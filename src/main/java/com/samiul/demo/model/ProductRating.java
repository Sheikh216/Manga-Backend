package com.samiul.demo.model;

import javax.persistence.*;

@Entity
public class ProductRating {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private products products;
    @OneToOne
    private User user;


    private Integer rating;

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    ;
    @Lob
    private String review;

    public Integer getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProducts(com.samiul.demo.model.products products) {
        this.products = products;
    }

    public void setUser(User User) {
        this.user = User;
    }

    public long getId() {
        return id;
    }

    public com.samiul.demo.model.products getProducts() {
        return products;
    }

    public User getUser() {
        return this.user;
    }
}
