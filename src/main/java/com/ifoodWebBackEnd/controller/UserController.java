package com.ifoodWebBackEnd.controller;

import com.ifoodWebBackEnd.domain.user.User;
import com.ifoodWebBackEnd.dtos.RestaurantResponseDTO;
import com.ifoodWebBackEnd.dtos.UserRequestDTO;
import com.ifoodWebBackEnd.dtos.UserResponseDTO;
import com.ifoodWebBackEnd.repositories.RoleRepository;
import com.ifoodWebBackEnd.repositories.UserRepository;
import com.ifoodWebBackEnd.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService service;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> newUser(@RequestBody UserRequestDTO data, JwtAuthenticationToken token) {
        return new ResponseEntity<UserResponseDTO>(service.saveUser(data, Long.parseLong(token.getName())), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateRestaurant(@PathVariable("id") Long id, @RequestBody UserRequestDTO data, JwtAuthenticationToken token) {
        return new ResponseEntity<UserResponseDTO>(service.updateUser(id, data, Long.parseLong(token.getName())), HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity deleteRestaurant(@PathVariable("id") Long id, JwtAuthenticationToken token) {
        service.deleteUser(id, Long.parseLong(token.getName()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers() {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}