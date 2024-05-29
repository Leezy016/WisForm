package com.wisform.entity;

import java.util.List;

public class LoginForm {
    private String username;
    private String password;
    private List<Long> permissions;
    private String identity;


    public LoginForm(String username, String password, String identity) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIdentity() {
        return this.identity;
    }
}

