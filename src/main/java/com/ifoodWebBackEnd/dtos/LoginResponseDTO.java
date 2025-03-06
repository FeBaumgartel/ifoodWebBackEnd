package com.ifoodWebBackEnd.dtos;

public record LoginResponseDTO(String token, Long expiresIn) {
}
