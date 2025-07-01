package com.ifoodWebBackEnd.controller;

import com.ifoodWebBackEnd.dtos.ProductRequestDTO;
import com.ifoodWebBackEnd.dtos.ProductResponseDTO;
import com.ifoodWebBackEnd.dtos.RestaurantResponseDTO;
import com.ifoodWebBackEnd.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO find(@PathVariable("id") Long id){
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_RESTAURANT', 'SCOPE_ADMIN')")
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductRequestDTO data, JwtAuthenticationToken token) {
        return new ResponseEntity<ProductResponseDTO>(service.saveProduct(data, token), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_RESTAURANT', 'SCOPE_ADMIN')")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDTO data, JwtAuthenticationToken token) {
        return new ResponseEntity<ProductResponseDTO>(service.updateProduct(id, data, token), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_RESTAURANT', 'SCOPE_ADMIN')")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id, JwtAuthenticationToken token) {
        service.deleteProduct(id, token);
        return new ResponseEntity(HttpStatus.OK);
    }
}
