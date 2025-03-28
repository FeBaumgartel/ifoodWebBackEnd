package com.ifoodWebBackEnd.dtos;

import com.ifoodWebBackEnd.domain.user.Role;
import com.ifoodWebBackEnd.domain.user.User;

import java.time.Instant;

public record UserResponseDTO(Long id, String name, String username, String password, Role role, User updateUser, Instant creationTimestamp, Instant updateTimestamp) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getRole(), user.getUpdateUser(), user.getCreationTimestamp(), user.getUpdateTimestamp());
    }
}
