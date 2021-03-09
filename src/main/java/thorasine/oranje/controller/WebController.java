package thorasine.oranje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import thorasine.oranje.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if(error != null) {
            errorMessage = error;
        }
        if(logout != null) {
            errorMessage = "You have been successfully logged out.";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
/*
    @RequestMapping(value = "/loginprocess", method = RequestMethod.POST)
    @ResponseBody
    public String registerUserAccount(HttpServletRequest request) {
        String response = request.getParameter("g-recaptcha-response");
        //captchaService.processResponse(response);
        System.out.println("ASDASSADDSADSDSDDS");
        return "asd";
    }
*/
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
