package ir.javapro.springsecurityjavapro.controller;


import ir.javapro.springsecurityjavapro.dto.UserRestLoginRequest;
import ir.javapro.springsecurityjavapro.dto.UserRestLoginResponse;
import ir.javapro.springsecurityjavapro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1")
@RequiredArgsConstructor
public class LoginRestController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<UserRestLoginResponse> login(@RequestBody UserRestLoginRequest userRestLoginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRestLoginRequest.getUsername(), userRestLoginRequest.getPassword())
        );
        UserRestLoginResponse userRestLoginResponse = userService.login(userRestLoginRequest);
        return ResponseEntity.ok(userRestLoginResponse);
    }
}
