package com.ifoodWebBackEnd.dtos;

import com.ifoodWebBackEnd.domain.user.Role;
import com.ifoodWebBackEnd.domain.user.User;

import java.time.Instant;

public record UserResponseDTO(Long id, String name, String username, String password, String street, String number, String city, String uf, Role role) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getStreet(), user.getNumber(), user.getCity(), user.getUf(), user.getRole());
    }
}
