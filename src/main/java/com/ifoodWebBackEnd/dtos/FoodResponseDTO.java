package com.ifoodWebBackEnd.dtos;

import com.ifoodWebBackEnd.domain.Food;
import com.ifoodWebBackEnd.domain.Restaurant;

public record FoodResponseDTO(Long id, String name, String image, Double price, Restaurant restaurant) {
    public FoodResponseDTO(Food food){
        this(food.getId(), food.getName(), food.getImage(), food.getPrice(), food.getRestaurant());
    }
}
