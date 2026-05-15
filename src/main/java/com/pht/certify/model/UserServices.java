package com.pht.certify.model;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.pht.certify.repository.UserRepo;

@Service
public class UserServices implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            var usrObj = user.get();
            return User.builder()
                .username(usrObj.getUsername())
                .password(usrObj.getPassword())
                .authorities("ROLE_" + usrObj.getRole().toUpperCase())
                .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}