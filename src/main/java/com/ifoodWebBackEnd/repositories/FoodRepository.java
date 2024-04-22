package com.ifoodWebBackEnd.repositories;

import com.ifoodWebBackEnd.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
