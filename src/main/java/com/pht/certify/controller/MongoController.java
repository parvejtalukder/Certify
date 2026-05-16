package com.pht.certify.controller;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pht.certify.exception.CertificateNotFoundException;
import com.pht.certify.model.Admin;
import com.pht.certify.model.Certificate;
import com.pht.certify.repository.CertificateRepo;
import com.pht.certify.repository.LogRepo;
import com.pht.certify.repository.UserRepo;
import com.pht.certify.services.LogService;

import jakarta.servlet.http.HttpSession;

@RestController
public class MongoController {

    private final CertificateRepo certificateRepo;
    private final UserRepo userRepo; 
    private final LogService logService;
    private final LogRepo logRepo;

    @Autowired
    public MongoController(CertificateRepo certificateRepo, UserRepo userRepo, LogService logService, LogRepo logRepo) {
        this.certificateRepo = certificateRepo;
        this.userRepo = userRepo;
        this.logService = logService;
        this.logRepo = logRepo;
    }

    // @GetMapping("/certificate/{id}")
    // public ResponseEntity<?> getById(@PathVariable String id) {

    //     Optional<Certificate> certificate = certificateRepo.findById(id);

    //     if (certificate.isEmpty()) {
    //         return ResponseEntity.status(404).body("Not found");
    //     }

    //     return ResponseEntity.ok(certificate.get());
    // }    

    @PostMapping("/add-certificate")
    public ResponseEntity<String> addCertificate(@RequestBody Certificate certificate, HttpSession session) {
        if(!session.getAttribute("role").equals("super") || !session.getAttribute("role").equals("admin")) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save certificate");
        }
        try {
            certificateRepo.save(certificate);
            logService.save(
                "ADD_CERTIFICATE",
                "Added certificate UID: " + certificate.getId(),
                LocalDateTime.now(),
                certificate.getIssuer()
            );
            return ResponseEntity.ok("Certificate saved successfully");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save certificate: " + e.getMessage());
        }
    }

}
