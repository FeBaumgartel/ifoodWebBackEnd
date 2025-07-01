package com.ifoodWebBackEnd.dtos;

import java.util.List;

public record OrderRequestDTO(Long userId, Long restaurantId, List<Long> ProductIds) {
}
