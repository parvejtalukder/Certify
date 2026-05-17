package com.pht.certify.model;

public class Admin extends User {

    private String institution;

    public Admin() {
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInstitution() {
        return this.institution;
    }

    public Admin(String name, String username, String email, String image, String institution) {
        setName(name);
        setUsername(username);
        setEmail(email);
        setImage(image);
        setInstitution(institution);
        setRole("admin");
    }

    
}
