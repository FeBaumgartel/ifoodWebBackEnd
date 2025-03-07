package com.ifoodWebBackEnd.dtos;

import com.ifoodWebBackEnd.domain.Food;
import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.domain.user.User;
import jakarta.persistence.JoinColumn;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

public record FoodResponseDTO(Long id, String name, Double price, String image, Restaurant restaurant, User user, Instant creationTimestamp, Instant updateTimestamp) {
    public FoodResponseDTO(Food food){
        this(food.getId(), food.getName(), food.getPrice(), food.getImage(), food.getRestaurant(), food.getUser(), food.getCreationTimestamp(), food.getUpdateTimestamp());
    }
}
