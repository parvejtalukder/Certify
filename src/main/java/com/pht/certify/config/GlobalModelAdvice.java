package com.pht.certify.config;
import com.pht.certify.main.Main;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("main")
    public Main main(Model model, HttpSession session) {
        String role = (String) session.getAttribute("role");
        String user = (String) session.getAttribute("username");
        String image = (String) session.getAttribute("image");

        model.addAttribute("currRole", role);
        model.addAttribute("currUser", user);
        model.addAttribute("currImage", image);
        return new Main(
                "Certify",
                "Certificate Management System",
                "PHT",
                "University Project"
        );
    }
}