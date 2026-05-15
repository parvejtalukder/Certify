package com.pht.certify.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.pht.certify.model.Admin;

public interface UserRepo extends MongoRepository<Admin, String> {

}
