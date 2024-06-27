package ir.javapro.springsecurityjavapro.controller;

import org.springframework.stereotype.Controller;
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
}
