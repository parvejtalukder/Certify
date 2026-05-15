package com.pht.certify.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.pht.certify.model.Certificate;

public interface CertificateRepo extends MongoRepository<Certificate, String> {

    Optional<Certificate> findById(String Id);

}
