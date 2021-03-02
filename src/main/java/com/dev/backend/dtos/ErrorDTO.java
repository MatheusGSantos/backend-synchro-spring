package com.dev.backend.dtos;

public class ErrorDTO {
    private String message;

	public String getMessage() {
		return message;
	}

	public ErrorDTO(String message) {
		this.message = message;
	}
}
