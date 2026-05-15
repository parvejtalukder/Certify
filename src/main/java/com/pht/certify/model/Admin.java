package com.pht.certify.model;

public class Admin extends User {

    private String institution;

    public Admin() {
    }

    public void setInst(String inst) {
        this.institution = inst;
    }

    public String getInst() {
        return this.institution;
    }

    public Admin(String name, String username, String email, String image, String inst) {
        setName(name);
        setUsername(username);
        setEmail(email);
        setImage(image);
        setInst(inst);
        setRole("admin");
    }

    
}
