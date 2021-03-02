package com.dev.backend.forms;

import com.dev.backend.models.User;

public class CreateUserForm extends BaseUserForm {
    
    private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public User toUser() {
        User newUser = new User();
        newUser.setName(this.getName());
        newUser.setEmail(this.getEmail());
        newUser.setPassword(this.getPassword());

        return newUser;
    }

}
