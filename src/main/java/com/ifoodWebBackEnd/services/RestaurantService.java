package com.ifoodWebBackEnd.services;

import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.dtos.RestaurantRequestDTO;
import com.ifoodWebBackEnd.dtos.RestaurantResponseDTO;
import com.ifoodWebBackEnd.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;
    
    public List<RestaurantResponseDTO> getAll() {
        return repository.findAll().stream().map(RestaurantResponseDTO::new).toList();
    }

    public RestaurantResponseDTO saveRestaurant(RestaurantRequestDTO data){
        Restaurant restaurant = new Restaurant(data);
        repository.save(restaurant);
        return new RestaurantResponseDTO(restaurant);
    }

    public RestaurantResponseDTO updateRestaurant(Long id, RestaurantRequestDTO data){
        Restaurant restaurant = repository.getReferenceById(id);
        restaurant.setName(data.name());
        restaurant.setImage(data.image());
        restaurant.setStreet(data.street());
        restaurant.setNumber(data.number());
        restaurant.setCity(data.city());
        return new RestaurantResponseDTO(restaurant);
    }

    public void deleteRestaurant(Long id){
        repository.deleteById(id);
    }
}
