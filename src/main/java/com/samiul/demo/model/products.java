package com.samiul.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.annotation.processing.Generated;
import javax.persistence.GeneratedValue;

@Entity
public class products {

    @Id
    @GeneratedValue
    private long id;
    private String productName;
    private String brand;
    private String description;
    private long Price;

    private int quantity;
    private String image;

    private Boolean premier = false;


    public Boolean getPremier() {
        return premier;
    }

    public void setPremier(Boolean premier) {
        this.premier = premier;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}