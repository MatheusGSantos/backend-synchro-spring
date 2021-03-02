package com.dev.backend.controllers;

import com.dev.backend.dtos.TokenDTO;
import com.dev.backend.forms.LoginForm;
import com.dev.backend.services.auth.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody LoginForm form) {
        UsernamePasswordAuthenticationToken loginData = form.converter();

        try {
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
