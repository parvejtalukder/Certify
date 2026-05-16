package com.pht.certify.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pht.certify.repository.CertificateRepo;

@Service
public class CertificateCount {

    @Autowired
    private CertificateRepo certificateRepo;
    public long getCertificateCount() {
        return certificateRepo.count();
    }

}
