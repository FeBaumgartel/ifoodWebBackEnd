package com.ifoodWebBackEnd.dtos;

import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.domain.user.User;

import java.time.Instant;

public record RestaurantResponseDTO(Long id, String name, String image, String street, String number, String city, User user, User updateUser, Instant creationTimestamp, Instant updateTimestamp) {
    public RestaurantResponseDTO(Restaurant restaurant){
        this(restaurant.getId(), restaurant.getName(), restaurant.getImage(), restaurant.getStreet(), restaurant.getNumber(), restaurant.getCity(), restaurant.getUser(), restaurant.getUpdateUser(), restaurant.getCreationTimestamp(), restaurant.getUpdateTimestamp());
    }
}
