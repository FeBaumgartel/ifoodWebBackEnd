package com.ifoodWebBackEnd.dtos;

import com.ifoodWebBackEnd.domain.Order;
import com.ifoodWebBackEnd.domain.Product;
import com.ifoodWebBackEnd.domain.Restaurant;
import com.ifoodWebBackEnd.domain.user.User;
import jakarta.persistence.JoinColumn;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

public record OrderResponseDTO(Long id, Restaurant restaurant, User User, List<Product> products) {
    public OrderResponseDTO(Order order){
        this(order.getId(), order.getRestaurant(), order.getUser(), order.getProducts());
    }
}
