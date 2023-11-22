package com.samiul.demo.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String name;
    private String email;

    private String password;

    private String newPassword;



    private String mobileNo;

    private Boolean Premier;


    public Boolean getPremier() {
        return Premier;
    }

    public void setPremier(Boolean premier) {
        Premier = premier;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    private Boolean login = false;

    private Boolean admin = false;





//    @ElementCollection
//    private List<Long> cart; // List of product IDs in the cart

    // Getters and setters for the cart field
//    public List<Long> getCart() {
//        return cart;
//    }
//
//    public void setCart(List<Long> cart) {
//        this.cart = cart;
//    }
//



    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


}
