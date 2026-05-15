package com.pht.certify.model;

public class User {

    private String name;
    private String username;
    private String email;
    private String image;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }


}
