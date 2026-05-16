package com.pht.certify.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.pht.certify.model.Admin;
import com.pht.certify.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class AddUserController {

    @Autowired
    private UserRepo userRepo;

@GetMapping("/admin/users/add")
public String addUserPage(Model model, HttpSession session) {
    String role = (String) session.getAttribute("role");
    if (!role.equals("super")) {
        return "redirect:/admin";
    }
    model.addAttribute("active", "users");
    return "admin/add-user";
}

@PostMapping("/admin/users/add")
public String addUser(@ModelAttribute Admin user, Model model, HttpSession session) {

    try {

        String role = (String) session.getAttribute("role");
        if (!"admin".equals(role) && !"super".equals(role)) {
            model.addAttribute("errorMessage", "You do not have the right to register someone.");
            return "admin/add-user";
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()
            || user.getPassword() == null || user.getPassword().isEmpty()
            || user.getEmail() == null || user.getEmail().isEmpty()) {
                model.addAttribute("errorMessage", "Must have an username/email/password");
            return "admin/add-user";    
        }

        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("errorMessage", "Username already exists");
            return "admin/add-user";
        }

        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("errorMessage", "Email already exists");
            return "admin/add-user";
        }

        userRepo.save(user);
        model.addAttribute("successMessage", "User added successfully");

    } catch (Exception e) {
        model.addAttribute("errorMessage", e.getMessage());
    }

    return "admin/add-user";
}
    
}