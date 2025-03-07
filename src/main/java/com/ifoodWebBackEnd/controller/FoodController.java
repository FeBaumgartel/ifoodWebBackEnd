package com.ifoodWebBackEnd.controller;

import com.ifoodWebBackEnd.dtos.FoodRequestDTO;
import com.ifoodWebBackEnd.dtos.FoodResponseDTO;
import com.ifoodWebBackEnd.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
    @PreAuthorize("hasAnyAuthority('SCOPE_RESTAURANT', 'SCOPE_ADMIN')")
    public ResponseEntity<FoodResponseDTO> saveFood(@RequestBody FoodRequestDTO data, JwtAuthenticationToken token) {
        return new ResponseEntity<FoodResponseDTO>(service.saveFood(data, Long.parseLong(token.getName())), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_RESTAURANT', 'SCOPE_ADMIN')")
    public ResponseEntity<FoodResponseDTO> updateFood(@PathVariable("id") Long id, @RequestBody FoodRequestDTO data, JwtAuthenticationToken token) {
        return new ResponseEntity<FoodResponseDTO>(service.updateFood(id, data, Long.parseLong(token.getName())), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_RESTAURANT', 'SCOPE_ADMIN')")
    public ResponseEntity deleteFood(@PathVariable("id") Long id, JwtAuthenticationToken token) {
        service.deleteFood(id, Long.parseLong(token.getName()));
        return new ResponseEntity(HttpStatus.OK);
    }
}
