package com.pht.certify.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pht.certify.model.Admin;
import com.pht.certify.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardAccess {

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/admin/users")
    public String manageUser(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("super")) {
            return "redirect:/admin?error=unauthorized";
        }
        try {
            if (page < 0) page = 0;
            int userPerPage = 5;
            Pageable pageable = PageRequest.of(page, userPerPage);
            Page<Admin> userPage = userRepo.findAll(pageable);
            model.addAttribute("users", userPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", userPage.getTotalPages());
            model.addAttribute("currRole", role);
            model.addAttribute("currUser", session.getAttribute("username"));
            model.addAttribute("currImage", session.getAttribute("image"));
            model.addAttribute("active", "users");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error While Visiting: " + e.getMessage());
        }
        return "admin/users";
    }

    @GetMapping("/admin/certificate/upload")
    public String UploadCertificate(Model model) {
        return "/admin/upload";
    }

}
