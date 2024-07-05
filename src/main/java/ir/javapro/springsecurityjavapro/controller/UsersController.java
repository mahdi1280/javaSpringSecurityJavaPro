package ir.javapro.springsecurityjavapro.controller;

import ir.javapro.springsecurityjavapro.dto.UserResponse;
import ir.javapro.springsecurityjavapro.model.User;
import ir.javapro.springsecurityjavapro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok("this is all user");
    }

    @GetMapping("/testAuthService")
    @PostAuthorize("@authService.checkLoadUser(#returnObject)")
    public ResponseEntity<?> testAuthService() {
        return ResponseEntity.ok().build();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable int id) {
        User user= userService.findById(id);
        UserResponse userResponse= UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
        return ResponseEntity.ok(userResponse);
    }
}
