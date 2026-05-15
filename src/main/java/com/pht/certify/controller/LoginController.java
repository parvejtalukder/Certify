package com.pht.certify.controller;
import com.pht.certify.main.Main;
import com.pht.certify.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

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
    
}