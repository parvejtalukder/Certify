package com.pht.certify.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.pht.certify.model.Admin;
import com.pht.certify.repository.UserRepo;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findByUsername("pht").isEmpty()) {
            Admin superAdmin = new Admin();
            superAdmin.setName("Parvej Husen Talukder");
            superAdmin.setUsername("pht");
            superAdmin.setPassword(passwordEncoder.encode("pass.gm.pht.certify"));
            superAdmin.setEmail("pht.cse@gmail.com");
            superAdmin.setImage("https://parvejhusentalukder.com/wp-content/uploads/2026/01/PHT.png");
            superAdmin.setInst("PHT");
            superAdmin.setRole("super");
            userRepo.save(superAdmin);
            System.out.println("Super Admin initialized: parvej / parvej123");
        }
    }
}
