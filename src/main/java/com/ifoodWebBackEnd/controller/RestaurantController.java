package com.ifoodWebBackEnd.controller;

import com.ifoodWebBackEnd.dtos.FoodResponseDTO;
import com.ifoodWebBackEnd.dtos.RestaurantRequestDTO;
import com.ifoodWebBackEnd.dtos.RestaurantResponseDTO;
import com.ifoodWebBackEnd.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<RestaurantResponseDTO> saveRestaurant(@RequestBody RestaurantRequestDTO data) {
        return new ResponseEntity<RestaurantResponseDTO>(service.saveRestaurant(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable("id") Long id, @RequestBody RestaurantRequestDTO data) {
        return new ResponseEntity<RestaurantResponseDTO>(service.updateRestaurant(id, data), HttpStatus.CREATED);
    }

    @DeleteMapping("/id")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity deleteRestaurant(@PathVariable("id") Long id) {
        service.deleteRestaurant(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/foods/{id}")
    public List<FoodResponseDTO> getFoodsByRestaurant(@PathVariable("id") Long restuarantId){
        return service.getFoodsByRestaurant(restuarantId);
    }
}
