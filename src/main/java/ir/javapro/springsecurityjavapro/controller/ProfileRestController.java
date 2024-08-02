package ir.javapro.springsecurityjavapro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/v2")
public class ProfileRestController {


    @GetMapping
    public ResponseEntity<String> getProfile() {
        return ResponseEntity.ok().body("Hello World");
    }
}
