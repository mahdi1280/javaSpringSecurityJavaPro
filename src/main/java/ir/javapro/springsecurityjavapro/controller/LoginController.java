package ir.javapro.springsecurityjavapro.controller;

import ir.javapro.springsecurityjavapro.dto.UserSaveRequest;
import ir.javapro.springsecurityjavapro.model.Role;
import ir.javapro.springsecurityjavapro.model.User;
import ir.javapro.springsecurityjavapro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(ModelMap modelMap) {
        modelMap.put("userSaveRequest" , new UserSaveRequest("1","1"));
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute @Valid UserSaveRequest userSaveRequest
            , BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }
        userService.save(convertUserSaveRequest(userSaveRequest));
        return "redirect:/login";
    }

    private User convertUserSaveRequest(UserSaveRequest userSaveRequest) {
        return User.builder()
                .username(userSaveRequest.username())
                .password(passwordEncoder.encode(userSaveRequest.password()))
                .enabled(Boolean.TRUE)
                .role(Arrays.asList(Role.USER,Role.ADMIN))
                .build();
    }
}
