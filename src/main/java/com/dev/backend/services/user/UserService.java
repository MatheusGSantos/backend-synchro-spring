package com.dev.backend.services.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dev.backend.dtos.ErrorDTO;
import com.dev.backend.dtos.ResponseDTO;
import com.dev.backend.dtos.UserDTO;
import com.dev.backend.forms.CreateUserForm;
import com.dev.backend.forms.UpdateProfileForm;
import com.dev.backend.models.User;
import com.dev.backend.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public ResponseEntity<ResponseDTO> createUser(CreateUserForm newUser) {
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO<ErrorDTO>(new ErrorDTO("Email already in use.")));
        }
        
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));

        return ResponseEntity.ok().
                body( new ResponseDTO<UserDTO>( new UserDTO( userRepository.save( newUser.toUser() ) ) ) );
    }

    public ResponseEntity<List<UserDTO>> getUsers(String id) {
        List<UserDTO> foundUsers = new ArrayList<>();

		if (id != null) {
            Optional<User> foundUser = userRepository.findById(id);
            
            if (foundUser.isPresent()) {
                foundUsers.add(new UserDTO(foundUser.get()));
                return ResponseEntity.ok().body(foundUsers);
            }
            
			return ResponseEntity.notFound().build();
		}

		userRepository.findAll().forEach((foundUser) -> {
            foundUsers.add(new UserDTO(foundUser));
        });

		return ResponseEntity.ok().body(foundUsers);
    }
    
    public UserDTO updateUser(String id, UpdateProfileForm userData) {
        return userRepository.findById(id).map(foundUser -> {
            foundUser = userData.updateUser(foundUser);
            return new UserDTO(userRepository.save(foundUser));
        }).orElseGet(() -> {
            return null;
        });
    }

    public ResponseEntity<?> deleteUser(String id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
