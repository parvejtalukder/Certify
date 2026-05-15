package com.pht.certify.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.pht.certify.model.Admin;
import com.pht.certify.model.Certificate;
import com.pht.certify.repository.CertificateRepo;
import com.pht.certify.repository.UserRepo;

@RestController
public class MongoController {

    private final CertificateRepo certificateRepo;
    private final UserRepo userRepo; 
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MongoController(CertificateRepo certificateRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.certificateRepo = certificateRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/certificate/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {

        Certificate certificate = certificateRepo.findById(id).orElse(null);
            if (certificate == null) {
                return ResponseEntity.status(404).body("Not found");
            }
        return ResponseEntity.ok(certificate);

    }

    @PostMapping("/add-certificate")
    public ResponseEntity<String> addCertificate(@RequestBody Certificate certificate) {
        try {
            certificateRepo.save(certificate);
            return ResponseEntity.ok("Certificate saved successfully");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save certificate: " + e.getMessage());
        }
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody Admin admin) {
        try {
            if (admin.getRole() == null || admin.getRole().isEmpty()) {
                admin.setRole("admin");
            }
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            userRepo.save(admin);
            return ResponseEntity.ok("User created successfully with role: " + admin.getRole());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create user: " + e.getMessage());
        }
    }

}
