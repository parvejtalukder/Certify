package com.pht.certify.repository;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.pht.certify.model.Admin;

public interface UserRepo extends MongoRepository<Admin, String> {
   Optional<Admin> findByUsername(String username);
   Optional<Admin> findByEmail(String email);
}
