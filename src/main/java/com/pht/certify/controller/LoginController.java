package com.pht.certify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pht.certify.main.Main;
import com.pht.certify.model.User;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model mdl) {

        Main main = new Main("Certify", "A web-based system to manage and verify certificates using UID.", "PHT", "University Project");

        User user = new User();

        mdl.addAttribute("main", main);
        mdl.addAttribute("active", "login");
        mdl.addAttribute("User", user);   

        return "login";
    }
}