package com.pht.certify.controller;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.pht.certify.datas.Datas;
// import com.pht.certify.datas.Datas;
import com.pht.certify.main.Main;

import jakarta.servlet.http.HttpSession;

@Controller
public class Index {

    @GetMapping("/")
    public String theIndex(Model mdl)  {
        Main main = new Main("Certify", "A web-based system to manage and verify certificates using UID.", "PHT", "University Project");
        ArrayList<Datas> arr = new ArrayList<>();
        arr.add(new Datas( "Instant Verification", "Real-time processing for immediate validation of digital and physical credentials.", "https://www.svgrepo.com/show/388625/speed-one.svg"
        ));
        arr.add(new Datas( "Secure Database", "Encrypted cloud storage utilizing institutional-grade security protocols for data safety.", "https://www.svgrepo.com/show/499816/database.svg"
        ));
        arr.add(new Datas( "Trusted Records", "Direct integration with academic institutions to provide 100% accurate information.", "https://www.svgrepo.com/show/195316/bank.svg"
        ));
        ArrayList<Datas> work = new ArrayList<>();
        work.add(new Datas("Enter UID", "Input the unique identifier or course code provided on the certificate."));
        work.add(new Datas("Fetch", "Our system searches through our verified records in milliseconds."));
        work.add(new Datas("Result", "Receive an official verification status with detailed student and course data."));


        mdl.addAttribute("main", main);
        mdl.addAttribute("feature", arr);
        mdl.addAttribute("workway", work);
        mdl.addAttribute("active", "home");
        return "index";
    }

    @GetMapping("/admin") 
    public String adminIndx(Model model, HttpSession session) {

        String role = (String) session.getAttribute("role");
        String user = (String) session.getAttribute("username");
        String image = (String) session.getAttribute("image");

        model.addAttribute("currRole", role);
        model.addAttribute("currUser", user);
        model.addAttribute("currImage", image);
        model.addAttribute("active", role);

        if (role == null || (!"admin".equals(role) && !"super".equals(role))) {
            return "redirect:/login";
        }
        model.addAttribute("content", "admin/index :: content");
        return "admin";
    }

}
