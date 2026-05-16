package com.pht.certify.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pht.certify.exception.CertificateNotFoundException;
import com.pht.certify.model.Certificate;   // ✅ Use your own Certificate model
import com.pht.certify.repository.CertificateRepo;

@Controller
public class CertificateController {

    @Autowired
    private CertificateRepo certificateRepo;

    @GetMapping("/verify")
    public String verifyPage() {
        return "admin/verify";
    }

    @PostMapping("/verify")
    public String verifySubmit(@RequestParam("id") String id, Model model) {
        try {
            Optional<Certificate> cert = certificateRepo.findById(id);

            if (cert.isPresent()) {
                model.addAttribute("certId", id);
                model.addAttribute("cert", cert.get());
            } else {
                throw new CertificateNotFoundException(id);
            }
        } catch (CertificateNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "admin/verify";
    }
}