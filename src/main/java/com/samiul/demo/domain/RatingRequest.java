package com.samiul.demo.domain;

import com.sun.istack.NotNull;

public class RatingRequest {
    @NotNull
    private Long orderId;
    @NotNull
    private Long productId;

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    @NotNull
    private String userName;
    @NotNull
    private Integer rating;
    private String  review;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getRating() {
        return rating;
    }



}
