package com.pht.certify.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.pht.certify.model.Log;

public interface LogRepo extends MongoRepository <Log, String> {

}
