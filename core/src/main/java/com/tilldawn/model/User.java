package com.tilldawn.model;

public class User {
    private String username;
    private String password;
    private SecurityQuestion securityQuestion = null;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
}
