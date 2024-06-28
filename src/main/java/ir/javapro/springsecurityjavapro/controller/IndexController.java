package ir.javapro.springsecurityjavapro.controller;

import ir.javapro.springsecurityjavapro.config.SecurityHelper;
import ir.javapro.springsecurityjavapro.dto.UserResponse;
import ir.javapro.springsecurityjavapro.model.User;
import ir.javapro.springsecurityjavapro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/index")
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;

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

    @GetMapping("/userList")
    public String usersPage(Model model) {
        List<User> users = userService.findAll();
        List<UserResponse> usersResponse = users.stream().map(user -> UserResponse.builder()
                .role(user.getRole())
                .id(user.getId())
                .username(user.getUsername())
                .build()).collect(Collectors.toList());
        model.addAttribute("userResponse",usersResponse);
        return "users";
    }
}
