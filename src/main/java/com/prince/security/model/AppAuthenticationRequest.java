package com.prince.security.model;


import java.io.Serializable;

public class AppAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -5456121589721215465L;

    private String username;
    private String password;
    private String email;

    public AppAuthenticationRequest() {
    }

    public AppAuthenticationRequest(String email, String username, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
