package com.samiul.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    @Lob
    private String faq;
    private boolean isReplied = false;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFaq(String faq) {
        this.faq = faq;
    }

    public void setReplied(boolean replied) {
        isReplied = replied;
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

    public String getFaq() {
        return faq;
    }

    public boolean isReplied() {
        return isReplied;
    }


}
