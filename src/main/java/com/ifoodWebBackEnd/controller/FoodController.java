package com.ifoodWebBackEnd.controller;

import com.ifoodWebBackEnd.dtos.FoodRequestDTO;
import com.ifoodWebBackEnd.dtos.FoodResponseDTO;
import com.ifoodWebBackEnd.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("foods")
public class FoodController {
    @Autowired
    private FoodService service;

    @GetMapping
    public List<FoodResponseDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_RESTAURANT')")
    public ResponseEntity<FoodResponseDTO> saveFood(@RequestBody FoodRequestDTO data) {
        return new ResponseEntity<FoodResponseDTO>(service.saveFood(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_RESTAURANT')")
    public ResponseEntity<FoodResponseDTO> updateFood(@PathVariable("id") Long id, @RequestBody FoodRequestDTO data) {
        return new ResponseEntity<FoodResponseDTO>(service.updateFood(id, data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_RESTAURANT')")
    public ResponseEntity deleteFood(@PathVariable("id") Long id) {
        service.deleteFood(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
