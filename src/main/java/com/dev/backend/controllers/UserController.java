package com.dev.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.dev.backend.dtos.ResponseDTO;
import com.dev.backend.dtos.UserDTO;
import com.dev.backend.forms.CreateUserForm;
import com.dev.backend.forms.UpdateProfileForm;
import com.dev.backend.services.user.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> list(@RequestParam(required = false) String id) {
		return userService.getUsers(id);
	}

	@PostMapping
	public ResponseEntity<ResponseDTO> create(@RequestBody CreateUserForm user) {
		return userService.createUser(user);
	}

	@PutMapping("/{id}")
	public UserDTO update(@PathVariable("id") String id, @RequestBody UpdateProfileForm user) {
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		return userService.deleteUser(id);
	}
}