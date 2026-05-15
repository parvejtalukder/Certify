package com.pht.certify.controller;
import com.pht.certify.main.Main;
import com.pht.certify.model.Admin;
import com.pht.certify.model.User;
import com.pht.certify.repository.UserRepo;
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
    public String loginSubmit(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        Admin admin = userRepo.findByUsername(username).orElse(null);
        if (admin != null && admin.getPassword().equals(password)) {
            return "redirect:/admin";
        } else {
            Main main = new Main(
                    "Certify",
                    "Certificate Management System",
                    "PHT",
                    "University Project"
            );
            model.addAttribute("main", main);
            model.addAttribute("active", "login");
            model.addAttribute("user", new User());
            model.addAttribute("error", "Invalid username or password");

            return "login";
        }
    }
}