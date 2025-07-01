package com.ifoodWebBackEnd.dtos;

public record UserRequestDTO(String name, String username, String password, String street, String number, String city, String uf, String role) {
}
