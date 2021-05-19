package com.boristenelsen.baureport.controller;

import com.boristenelsen.baureport.jwt.model.AuthenticationRequest;
import com.boristenelsen.baureport.jwt.model.AuthenticationResponse;
import com.boristenelsen.baureport.jwt.service.CustomUserDetailsService;
import com.boristenelsen.baureport.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {


    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    CustomUserDetailsService costumUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationReqeust) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReqeust.getUsername(), authenticationReqeust.getPassword()));

        }catch (BadCredentialsException e){

            throw new Exception("Benutzername oder Passwort nicht korrekt");
        };
        final UserDetails userDetails = costumUserDetailsService.loadUserByUsername(authenticationReqeust.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(Principal principal){
        return ResponseEntity.ok(principal.getName());
    }
}
