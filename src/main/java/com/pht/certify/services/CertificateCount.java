package com.pht.certify.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pht.certify.repository.CertificateRepo;
import jakarta.servlet.http.HttpSession;


@Service
public class CertificateCount {

    @Autowired
    private CertificateRepo certificateRepo;
    @Autowired
    private HttpSession session;

    public long getCertificateCount() {
        String email = (String) session.getAttribute("email");
        if (email == null || email.isBlank()) {
            return 0;
        }
        return certificateRepo.countByIssuerEmail(email);
    }
    public long getAllCertificateCount() {
        return certificateRepo.count();
    }
}