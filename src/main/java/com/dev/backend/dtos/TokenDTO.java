package com.dev.backend.dtos;

public class TokenDTO {
    private String token;
    private String authType;

    public TokenDTO(String token, String authType) {
        this.token = token;
        this.authType = authType;
    }

    public String getToken() {
        return token;
    }
    
	public String getAuthType() {
		return authType;
	}
    
}
