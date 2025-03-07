package com.ifoodWebBackEnd.services;

import com.ifoodWebBackEnd.domain.Food;
import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.dtos.FoodRequestDTO;
import com.ifoodWebBackEnd.dtos.FoodResponseDTO;
import com.ifoodWebBackEnd.repositories.FoodRepository;
import com.ifoodWebBackEnd.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository repository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserService userService;

    public List<FoodResponseDTO> getAll() {
        return repository.findAll().stream().map(FoodResponseDTO::new).toList();
    }

    public FoodResponseDTO saveFood(FoodRequestDTO data, Long updateUser){
        Food food = new Food(data, restaurantRepository.findRestaurantById(data.restaurantId()), userService.findUserById(updateUser));
        repository.save(food);
        return new FoodResponseDTO(food);
    }

    public FoodResponseDTO updateFood(Long id, FoodRequestDTO data, Long updateUser){
        Food food = repository.getReferenceById(id);
        food.setName(data.name());
        food.setImage(data.image());
        food.setPrice(data.price());
        food.setRestaurant(restaurantRepository.getReferenceById(data.restaurantId()));
        food.setUser(userService.findUserById(updateUser));
        return new FoodResponseDTO(food);
    }

    public void deleteFood(Long id, Long updateUser){
        Food food = repository.getReferenceById(id);
        food.setUser(userService.findUserById(updateUser));
        repository.deleteById(id);
    }
}
