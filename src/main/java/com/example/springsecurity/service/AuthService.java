package com.example.springsecurity.service;

import com.example.springsecurity.security.JwtRequest;
import com.example.springsecurity.security.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity< JwtResponse > login(JwtRequest request);
}
