package com.vas.aos.core.application.service;

import com.vas.aos.core.domain.entities.Order;

import java.util.UUID;

public interface GetOrderService {
    Order getById(UUID id);
}
