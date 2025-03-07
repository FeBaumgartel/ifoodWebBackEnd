package com.ifoodWebBackEnd.services;

import com.ifoodWebBackEnd.domain.user.Role;
import com.ifoodWebBackEnd.domain.user.User;
import com.ifoodWebBackEnd.dtos.FoodResponseDTO;
import com.ifoodWebBackEnd.dtos.UserRequestDTO;
import com.ifoodWebBackEnd.dtos.UserResponseDTO;
import com.ifoodWebBackEnd.repositories.RoleRepository;
import com.ifoodWebBackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public List<UserResponseDTO> getAll() {
        return repository.findAll().stream().map(UserResponseDTO::new).toList();
    }

    public UserResponseDTO saveUser(UserRequestDTO data, Long userId){
        User updateUser = this.findUserById(userId);
        Role role = roleRepository.findByName(data.role());
        User user = new User(data, passwordEncoder.encode(data.password()), role, updateUser);

        repository.save(user);
        return new UserResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO data, Long userId){
        User updateUser = this.findUserById(userId);
        Role role = roleRepository.findByName(data.role());

        User user = this.findUserById(id);
        user.setName(data.name());
        user.setUsername(data.username());
        user.setPassword(passwordEncoder.encode(data.password()));
        user.setRole(role);
        user.setUser(updateUser);
        return new UserResponseDTO(user);
    }

    public void deleteUser(Long id, Long userId){
        User user = this.findUserById(id);
        User updateUser = this.findUserById(userId);
        user.setUser(updateUser);
        repository.deleteById(id);
    }

    public User findUserById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
