package thorasine.oranje.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thorasine.oranje.persistence.repository.UserRepository;
import thorasine.oranje.security.login.LoginAttemptService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;

@Controller
public class WebController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginAttemptService loginAttemptService;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model){
        if(loginAttemptService.captchaRequired(request.getRemoteAddr())){
            model.addAttribute("requireCaptcha", true);
        }
        return "login";
    }

    @GetMapping("/info")
    public String infoPage(Model model, Principal principal){
        Date lastLogin = userRepository.findByUsername(principal.getName()).getLastLogin();
        model.addAttribute("lastLogin", lastLogin);
        return "info";
    }

    @GetMapping("/user")
    public String usercentralPage(){
        return "user";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/editor")
    public String editPage(){
        return "editor";
    }
}
