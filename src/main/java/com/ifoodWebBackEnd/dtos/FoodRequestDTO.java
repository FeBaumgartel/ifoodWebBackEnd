package com.ifoodWebBackEnd.dtos;

public record FoodRequestDTO(String name, String image, Double price, Long restaurantId) {
}
