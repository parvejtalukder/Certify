package com.pht.certify.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pht.certify.main.Main;

@Controller
public class Index {

    @GetMapping("/")
    public String theIndex(Model mdl)  {
        Main main = new Main("Certify", "A web-based system to manage and verify certificates using UID.", "PHT", "University Project");
        mdl.addAttribute("main", main);
        mdl.addAttribute("active", "home");
        return "index";
    }

}
