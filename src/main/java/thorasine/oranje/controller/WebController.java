package thorasine.oranje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import thorasine.oranje.repository.UserRepository;

import java.security.Principal;
import java.util.Date;

@Controller
public class WebController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/home")
    public String homePage(Model model, Principal principal){
        Date lastLogin = userRepository.findByUsername(principal.getName()).getLastLogin();
        // we should never have null, but in case it got destroyed the site should still load
        if(lastLogin == null){
            lastLogin = new Date();
        }
        model.addAttribute("lastLogin", lastLogin);
        return "home :: content";
    }

    @RequestMapping("/usercentral")
    public String usercentralPage(){
        return "usercentral :: content";
    }

    @RequestMapping("/admin")
    public String adminPage(){
        return "admin :: content";
    }

    @RequestMapping("/edit")
    public String editPage(){
        return "edit :: content";
    }
}
