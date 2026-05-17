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
            return "redirect:/admin";
        }
        try {
            int userPerPage = 5;
            Pageable pageable = PageRequest.of(page, userPerPage);
            Page<Admin> userPage = userRepo.findAll(pageable);
            model.addAttribute("users", userPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", userPage.getTotalPages());

            String user = (String) session.getAttribute("username");
            String image = (String) session.getAttribute("image");

            model.addAttribute("currRole", role);
            model.addAttribute("currUser", user);
            model.addAttribute("currImage", image);
            model.addAttribute("active", "users");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error While Visiting!");
        }
        return "admin/users";
    }

    @GetMapping("/admin/certificate/upload")
    public String UploadCertificate(Model model) {
        return "/admin/upload";
    }

}
