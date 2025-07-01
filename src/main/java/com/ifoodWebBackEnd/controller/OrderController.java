package com.ifoodWebBackEnd.controller;

import com.ifoodWebBackEnd.dtos.ProductRequestDTO;
import com.ifoodWebBackEnd.dtos.ProductResponseDTO;
import com.ifoodWebBackEnd.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<OrderResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO find(@PathVariable("id") Long id){
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_CUSTOMER')")
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductRequestDTO data, JwtAuthenticationToken token) {
        return new ResponseEntity<ProductResponseDTO>(service.saveProduct(data, token), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_CUSTOMER', 'SCOPE_ADMIN')")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id, JwtAuthenticationToken token) {
        service.deleteProduct(id, token);
        return new ResponseEntity(HttpStatus.OK);
    }
}
