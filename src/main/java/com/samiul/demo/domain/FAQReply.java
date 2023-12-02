package com.samiul.demo.domain;

import com.sun.istack.NotNull;

public class FAQReply {
    @NotNull
    private String reply;

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }
}
