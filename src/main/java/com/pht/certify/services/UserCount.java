package com.pht.certify.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pht.certify.repository.UserRepo;

@Service
public class UserCount {
    @Autowired
    private UserRepo userRepo;
    public long getUserCount() {
        return userRepo.count();
    }
}
