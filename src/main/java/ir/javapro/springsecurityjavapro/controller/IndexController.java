package ir.javapro.springsecurityjavapro.controller;

import ir.javapro.springsecurityjavapro.config.SecurityHelper;
import ir.javapro.springsecurityjavapro.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public String init() {
        return "index";
    }

    @GetMapping("/products")
    public String products(){
        return "products";
    }

    @GetMapping("/userDetails")
    public String userDetails(Model model) {
        model.addAttribute("user", SecurityHelper.getCurrentUser());
        return "userDetails";
    }
}
