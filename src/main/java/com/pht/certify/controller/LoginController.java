package com.pht.certify.controller;
import com.pht.certify.main.Main;
import com.pht.certify.model.Admin;
import com.pht.certify.model.User;
import com.pht.certify.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String login(Model model) {
        Main main = new Main(
                "Certify",
                "Certificate Management System",
                "PHT",
                "University Project"
        );
        model.addAttribute("main", main);
        model.addAttribute("active", "login");
        model.addAttribute("user", new User());

        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Optional<Admin> adminOpt = userRepo.findByUsername(username);
        Main main = new Main(
                "Certify",
                "Certificate Management System",
                "PHT",
                "University Project"
        );
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (admin.getPassword().equals(password)) {
                    model.addAttribute("admin", admin);
                    session.setAttribute("userId", admin.getId());
                    session.setAttribute("username", admin.getUsername());
                    session.setAttribute("email", admin.getEmail());
                    session.setAttribute("role", admin.getRole());
                    session.setAttribute("image", admin.getImage());
                return "redirect:/admin";
            }
        } 
        model.addAttribute("main", main);
        model.addAttribute("active", "login");
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
}