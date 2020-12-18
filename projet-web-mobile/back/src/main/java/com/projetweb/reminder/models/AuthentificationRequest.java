package com.projetweb.reminder.models;

public class AuthentificationRequest {

    private String password;
    private String username;

    public AuthentificationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
