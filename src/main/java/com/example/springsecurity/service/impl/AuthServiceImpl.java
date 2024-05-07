package com.example.springsecurity.service.impl;

import com.example.springsecurity.security.JwtRequest;
import com.example.springsecurity.security.JwtResponse;
import com.example.springsecurity.security.JwtHelper;
import com.example.springsecurity.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper helper;



    public AuthServiceImpl(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtHelper helper) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.helper = helper;
    }



    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @Override
    public ResponseEntity< JwtResponse > login(JwtRequest request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
        JwtResponse response = new JwtResponse(token,userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
