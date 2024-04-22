package com.ifoodWebBackEnd.services;

import com.ifoodWebBackEnd.domain.Food;
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

    public List<FoodResponseDTO> getAll() {
        return repository.findAll().stream().map(FoodResponseDTO::new).toList();
    }

    public FoodResponseDTO saveFood(FoodRequestDTO data){
        Food food = new Food(data);
        food.setRestaurant(restaurantRepository.getReferenceById(data.restaurantId()));
        repository.save(food);
        return new FoodResponseDTO(food);
    }

    public FoodResponseDTO updateFood(Long id, FoodRequestDTO data){
        Food food = repository.getReferenceById(id);
        food.setName(data.name());
        food.setImage(data.image());
        food.setPrice(data.price());
        food.setRestaurant(restaurantRepository.getReferenceById(data.restaurantId()));
        return new FoodResponseDTO(food);
    }

    public void deleteFood(Long id){
        repository.deleteById(id);
    }
}
