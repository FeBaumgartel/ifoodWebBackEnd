package com.ifoodWebBackEnd.repositories;

import com.ifoodWebBackEnd.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Product, Long> {
    List<Product> findByRestaurantId(Long restaurantId);
}
