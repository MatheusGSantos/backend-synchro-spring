package com.dev.backend.forms;

import com.dev.backend.models.User;

public class UpdateProfileForm extends BaseUserForm {

    public User updateUser(User user) {
        user.setEmail(this.getEmail());
        user.setName(this.getName());
        return user;
    }
    
}