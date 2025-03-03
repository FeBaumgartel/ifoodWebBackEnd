package com.ifoodWebBackEnd.dtos;

import com.ifoodWebBackEnd.domain.user.Role;

public record UserRequestDTO(String name, String email, String password, Role role) {
}
