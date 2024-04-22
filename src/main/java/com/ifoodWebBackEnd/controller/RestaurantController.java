package com.irestaurantWebBackEnd.controller;

import com.ifoodWebBackEnd.dtos.RestaurantRequestDTO;
import com.ifoodWebBackEnd.dtos.RestaurantResponseDTO;
import com.ifoodWebBackEnd.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @GetMapping
    public List<RestaurantResponseDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> saveRestaurant(@RequestBody RestaurantRequestDTO data) {
        return new ResponseEntity<RestaurantResponseDTO>(service.saveRestaurant(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable("id") Long id, @RequestBody RestaurantRequestDTO data) {
        return new ResponseEntity<RestaurantResponseDTO>(service.updateRestaurant(id, data), HttpStatus.CREATED);
    }

    @DeleteMapping("/id")
    public ResponseEntity deleteRestaurant(@PathVariable("id") Long id) {
        service.deleteRestaurant(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
