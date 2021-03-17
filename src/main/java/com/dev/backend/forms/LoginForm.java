package com.dev.backend.forms;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
    
  private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
