package com.ifoodWebBackEnd.dtos;

import com.ifoodWebBackEnd.domain.Product;
import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.domain.user.User;
import jakarta.persistence.JoinColumn;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

public record ProductResponseDTO(Long id, String name, Double price, String image, Restaurant restaurant) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice(), product.getImage(), product.getRestaurant());
    }
}
