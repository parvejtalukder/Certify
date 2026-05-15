package com.pht.certify.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pht.certify.model.Admin;
import com.pht.certify.model.Certificate;
import com.pht.certify.repository.CertificateRepo;
import com.pht.certify.repository.UserRepo;

@RestController
public class MongoController {

    private final CertificateRepo certificateRepo;
    private final UserRepo userRepo;

    @Autowired
    public MongoController(CertificateRepo certificateRepo, UserRepo userRepo) {
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
            userRepo.save(admin);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create user: " + e.getMessage());
        }
    }
}