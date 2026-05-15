package com.pht.certify.model;

public class Admin extends User {

    private boolean active;

    public Admin(String name, String username, String email, String image) {
        setName(name);
        setUsername(username);
        setEmail(email);
        setImage(image);
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public boolean canManageCertificates() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }
}