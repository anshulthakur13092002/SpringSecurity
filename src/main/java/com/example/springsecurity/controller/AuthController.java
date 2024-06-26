package com.example.springsecurity.controller;



import com.example.springsecurity.security.JwtRequest;
import com.example.springsecurity.security.JwtResponse;
import com.example.springsecurity.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity < JwtResponse > login(@RequestBody JwtRequest request) {
        return this.authService.login(request);

    }

}
