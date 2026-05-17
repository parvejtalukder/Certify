package com.pht.certify.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.pht.certify.model.Certificate;

public interface CertificateRepo extends MongoRepository<Certificate, String> {
    long countByIssuerEmail(String issuerEmail);
    Page<Certificate> findByIssuerEmail(String issuerEmail, Pageable pageable);
}