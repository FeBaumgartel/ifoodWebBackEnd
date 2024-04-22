package com.ifoodWebBackEnd.dtos;

import com.ifoodWebBackEnd.domain.Restaurant;

public record RestaurantResponseDTO(Long id, String name, String image, String street, String number, String city) {
    public RestaurantResponseDTO(Restaurant restaurant){
        this(restaurant.getId(), restaurant.getName(), restaurant.getImage(), restaurant.getStreet(), restaurant.getNumber(), restaurant.getCity());
    }
}
