package com.dev.backend.services.auth;

import java.util.Date;

import com.dev.backend.models.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
    
    @Value("${spring.jwt.expiration}")
    private String expiration;

    @Value("${spring.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Date startDate = new Date();
        Date expirationDate = new Date(startDate.getTime() + Long.parseLong(expiration));

        return Jwts.builder().setIssuer("Backend API").setSubject(loggedUser.getId()).setIssuedAt(startDate)
                .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }
    
    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
