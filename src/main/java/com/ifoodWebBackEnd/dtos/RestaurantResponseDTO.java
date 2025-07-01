package com.ifoodWebBackEnd.dtos;

import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.domain.user.User;

import java.time.Instant;

public record RestaurantResponseDTO(Long id, String name, String image, String street, String number, String city, String uf, User user) {
    public RestaurantResponseDTO(Restaurant restaurant){
        this(restaurant.getId(), restaurant.getName(), restaurant.getImage(), restaurant.getStreet(), restaurant.getNumber(), restaurant.getCity(), restaurant.getUf(), restaurant.getUser());
    }
}
