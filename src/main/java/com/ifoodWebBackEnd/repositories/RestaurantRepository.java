package com.ifoodWebBackEnd.repositories;

import com.ifoodWebBackEnd.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findRestaurantById(Long id);
}
