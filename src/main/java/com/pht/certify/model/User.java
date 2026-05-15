package com.pht.certify.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    private String image;
    private String password;
    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

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
