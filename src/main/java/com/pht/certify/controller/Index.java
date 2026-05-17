package com.pht.certify.controller;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.pht.certify.datas.Datas;
import com.pht.certify.main.Main;
import com.pht.certify.model.Stat;
import com.pht.certify.services.CertificateCount;
import com.pht.certify.services.LogService;
import com.pht.certify.services.UserCount;
import jakarta.servlet.http.HttpSession;

@Controller
public class Index {

    @Autowired
    private CertificateCount certificateCount;
    @Autowired
    private LogService logService;
    @Autowired
    private UserCount userCount;

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
        String email = (String) session.getAttribute("email");
        // System.out.print(email);

        model.addAttribute("currRole", role);
        model.addAttribute("currUser", user);
        model.addAttribute("currEmail", email);
        model.addAttribute("currImage", image);
        model.addAttribute("active", "dashboard");

        if (role == null || (!"admin".equals(role) && !"super".equals(role))) {
            return "redirect:/login";
        }

        ArrayList <Stat> dataArr = new ArrayList<>();
        // Stat stat = new Stat();
        long log, cert, usrr = 0;
        if (role.equals("super")) {
            long logCnt = logService.getLogCount();
            long certCnt = certificateCount.getCertificateCount();
            long certAll = certificateCount.getAllCertificateCount();
            long userCnt = userCount.getUserCount();
            Stat All = new Stat();
            All.setCount(certAll);
            All.setDesc("All Certificates");
            dataArr.add(All);
            Stat stat = new Stat();
            stat.setCount(certCnt);
            stat.setDesc("My Certificates");
            dataArr.add(stat);
            Stat usr = new Stat();
            usr.setCount(userCnt);
            usr.setDesc("Total Users");
            dataArr.add(usr);
            // Stat logs = new Stat();
            // logs.setCount(logCnt);
            // logs.setDesc("Total Logs");
            // dataArr.add(logs);
            cert = certCnt;
            log = logCnt;
            usrr = userCnt;
        } else {
            long certCnt = certificateCount.getCertificateCount();
            Stat stat = new Stat();
            stat.setCount(certCnt);
            stat.setDesc("My Certificates");
            dataArr.add(stat);
            cert = certCnt;
            log = 0;
            usrr = 0;
            // long logCnt = logService.getLogCount();
            // Stat logs = new Stat();
            // logs.setCount(logCnt);
            // logs.setDesc("Total Logs");
            // dataArr.add(logs);
        }
        model.addAttribute("dataArr", dataArr);
        return "admin/index";
    }

}
