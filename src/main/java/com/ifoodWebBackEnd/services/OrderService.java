package com.ifoodWebBackEnd.services;

import com.ifoodWebBackEnd.domain.Product;
import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.dtos.ProductRequestDTO;
import com.ifoodWebBackEnd.dtos.ProductResponseDTO;
import com.ifoodWebBackEnd.repositories.ProductRepository;
import com.ifoodWebBackEnd.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserService userService;

    public List<ProductResponseDTO> getAll() {
        return repository.findAll().stream().map(ProductResponseDTO::new).toList();
    }

    public ProductResponseDTO getById(Long id) {
        return new ProductResponseDTO(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public ProductResponseDTO saveProduct(ProductRequestDTO data, JwtAuthenticationToken token){
        Long updateUser = Long.parseLong(token.getName());
        Restaurant restaurant = restaurantRepository.findByUserId(updateUser);
        if(restaurant == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Product product = new Product(data, restaurant);
        repository.save(product);
        return new ProductResponseDTO(product);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO data, JwtAuthenticationToken token){
        Long updateUser = Long.parseLong(token.getName());
        Product product = repository.getReferenceById(id);
        if(updateUser != product.getRestaurant().getUser().getId() && token.getToken().getClaimAsString("scope") == "RESTAURANT"){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        product.setName(data.name());
        product.setImage(data.image());
        product.setPrice(data.price());
        return new ProductResponseDTO(product);
    }

    public void deleteProduct(Long id, JwtAuthenticationToken token){
        Long updateUser = Long.parseLong(token.getName());
        Product product = repository.getReferenceById(id);
        if(updateUser != product.getRestaurant().getUser().getId() && token.getToken().getClaimAsString("scope") == "RESTAURANT"){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        repository.deleteById(id);
    }
}
