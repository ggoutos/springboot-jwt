package com.example.demo.controller;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.login(request);
        return ok(authenticationResponse);
    }
}
