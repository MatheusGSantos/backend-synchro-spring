package com.dev.backend.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.backend.models.User;
import com.dev.backend.repositories.UserRepository;
import com.dev.backend.services.auth.TokenService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public TokenAuthFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        
        String token = getTokenFromHeader(request);
        boolean valid = tokenService.isValid(token);

        if (valid) {
            authenticateClient(token);
        }

        filterChain.doFilter(request, response);
		
	}

    private void authenticateClient(String token) {
        String userId = tokenService.getUserId(token);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

        return token.substring(7, token.length());
	}

}
