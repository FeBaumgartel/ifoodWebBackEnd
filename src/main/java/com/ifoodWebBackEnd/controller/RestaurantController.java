package com.ifoodWebBackEnd.controller;

import com.ifoodWebBackEnd.dtos.ProductResponseDTO;
import com.ifoodWebBackEnd.dtos.RestaurantRequestDTO;
import com.ifoodWebBackEnd.dtos.RestaurantResponseDTO;
import com.ifoodWebBackEnd.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @GetMapping
    public List<RestaurantResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RestaurantResponseDTO find(@PathVariable("id") Long id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable("id") Long id, @RequestBody RestaurantRequestDTO data, JwtAuthenticationToken token) {
        return new ResponseEntity<RestaurantResponseDTO>(service.updateRestaurant(id, data), HttpStatus.OK);
    }

    @DeleteMapping("/id")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity deleteRestaurant(@PathVariable("id") Long id, JwtAuthenticationToken token) {
        service.deleteRestaurant(id, Long.parseLong(token.getName()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public List<ProductResponseDTO> getProductsByRestaurant(@PathVariable("id") Long restuarantId){
        return service.getProductsByRestaurant(restuarantId);
    }
}
