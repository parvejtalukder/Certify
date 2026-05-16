package com.pht.certify.config;

import com.pht.certify.main.Main;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("main")
    public Main main() {
        return new Main(
                "Certify",
                "Certificate Management System",
                "PHT",
                "University Project"
        );
    }
}