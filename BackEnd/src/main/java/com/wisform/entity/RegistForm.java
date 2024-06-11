package com.wisform.entity;

public class RegistForm {
    private String username;
    private String password;
    private String role;
    private String department;


    public RegistForm(String username, String password, String role, String department) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIdentity() {
        return role;}

    public String getDepartment(){
        return department;
    }
}