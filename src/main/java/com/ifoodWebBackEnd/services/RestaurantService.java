package com.ifoodWebBackEnd.services;

import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.dtos.FoodResponseDTO;
import com.ifoodWebBackEnd.dtos.RestaurantRequestDTO;
import com.ifoodWebBackEnd.dtos.RestaurantResponseDTO;
import com.ifoodWebBackEnd.repositories.FoodRepository;
import com.ifoodWebBackEnd.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserService userService;
    
    public List<RestaurantResponseDTO> getAll() {
        return repository.findAll().stream().map(RestaurantResponseDTO::new).toList();
    }

    public RestaurantResponseDTO saveRestaurant(RestaurantRequestDTO data, Long updateUser){
        Restaurant restaurant = new Restaurant(data, userService.findUserById(updateUser));
        repository.save(restaurant);
        return new RestaurantResponseDTO(restaurant);
    }

    public RestaurantResponseDTO updateRestaurant(Long id, RestaurantRequestDTO data, Long updateUser){
        Restaurant restaurant = repository.getReferenceById(id);
        restaurant.setName(data.name());
        restaurant.setImage(data.image());
        restaurant.setStreet(data.street());
        restaurant.setNumber(data.number());
        restaurant.setCity(data.city());
        restaurant.setUser(userService.findUserById(updateUser));
        return new RestaurantResponseDTO(restaurant);
    }

    public void deleteRestaurant(Long id, Long updateUser){

        Restaurant restaurant = repository.getReferenceById(id);
        restaurant.setUser(userService.findUserById(updateUser));
        repository.deleteById(id);
    }

    public List<FoodResponseDTO> getFoodsByRestaurant(Long restaurantId){
        return foodRepository.findByRestaurantId(restaurantId).stream().map(FoodResponseDTO::new).toList();
    }
}
