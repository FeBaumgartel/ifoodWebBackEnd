package com.ifoodWebBackEnd.services;

import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.domain.user.User;
import com.ifoodWebBackEnd.dtos.ProductResponseDTO;
import com.ifoodWebBackEnd.dtos.RestaurantRequestDTO;
import com.ifoodWebBackEnd.dtos.RestaurantResponseDTO;
import com.ifoodWebBackEnd.repositories.ProductRepository;
import com.ifoodWebBackEnd.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    public List<RestaurantResponseDTO> getAll() {
        return repository.findAll().stream().map(RestaurantResponseDTO::new).toList();
    }

    public RestaurantResponseDTO getById(Long id) {
        return new RestaurantResponseDTO(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public void saveRestaurant(RestaurantRequestDTO data, User user){
        Restaurant restaurant = new Restaurant(data, user);
        repository.save(restaurant);
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

    public void deleteRestaurant(Long id, Long updateUser){

        Restaurant restaurant = repository.getReferenceById(id);
        restaurant.setUser(userService.findUserById(updateUser));
        repository.deleteById(id);
    }

    public List<ProductResponseDTO> getProductsByRestaurant(Long restaurantId){
        return productRepository.findByRestaurantId(restaurantId).stream().map(ProductResponseDTO::new).toList();
    }
}
