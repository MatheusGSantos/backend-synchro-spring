package com.dev.backend.dtos;

import com.dev.backend.models.User;

public class UserDTO {
	private String name;
	private String email;

	public UserDTO() {}
	
	public UserDTO(User user) {
		setName(user.getName());
		setEmail(user.getEmail());
	}

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