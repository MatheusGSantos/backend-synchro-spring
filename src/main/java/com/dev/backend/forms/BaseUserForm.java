package com.dev.backend.forms;

public class BaseUserForm {
    private String name;
    private String email;
	
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
	public void setEmail(String email) {
		this.email = email;
	}

}
