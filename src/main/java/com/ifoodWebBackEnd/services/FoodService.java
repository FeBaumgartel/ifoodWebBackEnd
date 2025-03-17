package com.ifoodWebBackEnd.services;

import com.ifoodWebBackEnd.domain.Food;
import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.dtos.FoodRequestDTO;
import com.ifoodWebBackEnd.dtos.FoodResponseDTO;
import com.ifoodWebBackEnd.repositories.FoodRepository;
import com.ifoodWebBackEnd.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public FoodResponseDTO saveFood(FoodRequestDTO data, JwtAuthenticationToken token){
        Long updateUser = Long.parseLong(token.getName());
        Restaurant restaurant = restaurantRepository.findRestaurantById(updateUser);
        if(restaurant == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Food food = new Food(data, restaurant, userService.findUserById(updateUser));
        repository.save(food);
        return new FoodResponseDTO(food);
    }

    public FoodResponseDTO updateFood(Long id, FoodRequestDTO data, JwtAuthenticationToken token){
        Long updateUser = Long.parseLong(token.getName());
        Food food = repository.getReferenceById(id);
        if(updateUser != food.getRestaurant().getId() && token.getToken().getClaimAsString("scope") == "RESTAURANT"){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        food.setName(data.name());
        food.setImage(data.image());
        food.setPrice(data.price());
        food.setUpdateUser(userService.findUserById(updateUser));
        return new FoodResponseDTO(food);
    }

    public void deleteFood(Long id, JwtAuthenticationToken token){
        Long updateUser = Long.parseLong(token.getName());
        Food food = repository.getReferenceById(id);
        food.setUpdateUser(userService.findUserById(updateUser));
        if(updateUser != food.getRestaurant().getId() && token.getToken().getClaimAsString("scope") == "RESTAURANT"){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        repository.deleteById(id);
    }
}
