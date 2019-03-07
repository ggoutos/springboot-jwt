package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @Transactional
    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            String username = request.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
            User user = this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
            String token = jwtTokenProvider.createToken(username, user.getRoles());

            return new AuthenticationResponse(username, token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
