package com.pht.certify.controller;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pht.certify.exception.CertificateNotFoundException;
import com.pht.certify.model.Admin;
import com.pht.certify.model.Certificate;
import com.pht.certify.model.Log;
import com.pht.certify.repository.CertificateRepo;
import com.pht.certify.repository.LogRepo;
import com.pht.certify.repository.UserRepo;
import com.pht.certify.services.LogService;
import com.pht.certify.services.PdfService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CertificateController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LogRepo LogRepo;

    @Autowired
    private LogService logService;

    @Autowired
    private CertificateRepo certificateRepo;
    
    @Autowired
    private PdfService pdfService;

    private String extract(String text, String key) {
        int start = text.indexOf(key);
        if (start == -1) {
            return "";
        }
        start = start + key.length();
        int end = text.indexOf("\n", start);
        if (end == -1) {
            end = text.length();
        }
        return text.substring(start, end).replace(":", "").trim();
    }

    @GetMapping("/verify")
    public String verifyPage(Model model) {
        model.addAttribute("active", "verify");
        return "admin/verify";
    } 

    @PostMapping("/verify")
    public String verifySubmit(@RequestParam("id") String id, Model model) {
        
        Certificate cert = certificateRepo.findById(id).orElse(null);

        if (cert != null) {
            model.addAttribute("certId", id);
            model.addAttribute("cert", cert);
        } else {
            model.addAttribute("errorMessage", "Certificate not found with ID: " + id);
        }
        model.addAttribute("active", "verify");
        return "admin/verify";
    }

    @GetMapping("/admin/certificate")
    public String certificate(Model model,  @RequestParam(defaultValue = "0") int page, HttpSession session) {

        String role = (String) session.getAttribute("role");
        String email = (String) session.getAttribute("email"); // FIXED

        if (role == null || (!role.equals("admin") && !role.equals("super"))) {
            return "redirect:/admin";
        }

        try {
            int certificatePerPage = 5;
            Pageable pageable = PageRequest.of(page, certificatePerPage);
            Page<Certificate> certificatesPage;
            if ("admin".equals(role)) {
                if (email == null) return "redirect:/admin";
                certificatesPage = certificateRepo.findByIssuerEmail(email, pageable);
            } else {
                certificatesPage = certificateRepo.findAll(pageable);
            }
            model.addAttribute("certificates", certificatesPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", certificatesPage.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("active", "certificates");
        return "admin/certificates";
    }

    @PostMapping("/admin/certificate/upload")
    public String UploadCertificate(@RequestParam("file") MultipartFile file, Model model, HttpSession session) {
        
        String email = (String) session.getAttribute("email");
        Optional<Admin> user = userRepo.findByEmail(email);
        String issuerName = "";
        String issuerEmail = "";
        if (user.isPresent()) {
            Admin admin = user.get();
            issuerName = admin.getInstitution();
            issuerEmail = admin.getEmail();
        }
        String text = pdfService.extractText(file);
        Certificate cert = new Certificate();
        cert.setStudentName(extract(text, "Name"));
        cert.setStudentId(extract(text, "ID"));
        cert.setCourse(extract(text, "Course"));
        cert.setGrade(extract(text, "Grade"));
        cert.setIssuer(extract(text, "Issuer"));
        if (cert.getIssuer().isEmpty()) {
            cert.setIssuer(issuerName);
        }

        cert.setIssueDate(LocalTime.now().toString());
        cert.setIssuerEmail(issuerEmail);
        try {
            Certificate saved = certificateRepo.save(cert);
            String certificateId = saved.getId();
            // LogRepo log = new LogRepo);
            Admin admin = user.get();
            String ID = admin.getId();
            logService.save("ADD", "CERTIFICATE UPLOADED", LocalDateTime.now().toString(), ID);
            model.addAttribute("certificateId", certificateId);
            model.addAttribute("certificate", saved);
            model.addAttribute("successMessage", "Certificate uploaded successfully.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Upload failed: " + e.getMessage());
        }
        model.addAttribute("active", "certificate");
        model.addAttribute("active", "certificates");
        return "/admin/upload";
    }

    @PostMapping("/admin/certificate/delete/{id}")
    public String deleteCertificate(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes) {
        String role = (String) session.getAttribute("role");
        String email = (String) session.getAttribute("email");
        if (!"super".equals(role) && !"admin".equals(role)) {
            return "redirect:/admin";
        }
        try {
            Optional<Admin> user = userRepo.findByEmail(email);
            Admin admin = user.orElse(null);
            String ID = (admin != null) ? admin.getId() : "UNKNOWN";
            Optional<Certificate> certOpt = certificateRepo.findById(id);

            if (certOpt.isPresent()) {
                Certificate cert = certOpt.get();
                if ("super".equals(role) || cert.getIssuerEmail().equals(email)) {
                    certificateRepo.deleteById(id);
                    logService.save(
                        "DELETE",
                        "CERTIFICATE DELETED",
                        LocalDateTime.now().toString(),
                        ID
                    );
                    redirectAttributes.addFlashAttribute("successMessage", "Certificate deleted successfully.");
                } else {
                    redirectAttributes.addFlashAttribute("errorMessage", "You are not allowed to delete this certificate.");
                }
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Certificate not found.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Delete failed: " + e.getMessage());
        }
        return "redirect:/admin/certificate";
    }

}