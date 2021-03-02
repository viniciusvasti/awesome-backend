package com.vas.aos.core.application.repository;

import com.vas.aos.core.domain.entities.Order;

import java.util.Optional;
import java.util.UUID;

public interface GetOrderRepository {
    Optional<Order> getById(UUID id);
}
