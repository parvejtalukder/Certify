package com.pht.certify.config;

import com.pht.certify.main.Main;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("main")
    public Main main(Model model, HttpSession session) {
        String role = (String) session.getAttribute("role");
        String user = (String) session.getAttribute("username");
        String image = (String) session.getAttribute("image");
        String email = (String) session.getAttribute("email");
        model.addAttribute("currRole", role != null ? role : "");
        model.addAttribute("currUser", user != null ? user : "Guest");
        model.addAttribute("currImage", image != null ? image : "");
        model.addAttribute("currEmail", email != null ? email : "");
        return new Main(
                "Certify",
                "Certificate Management System",
                "PHT",
                "University Project"
        );
    }
}