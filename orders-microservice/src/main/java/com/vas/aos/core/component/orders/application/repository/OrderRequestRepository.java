package com.vas.aos.core.component.orders.application.repository;

import com.vas.aos.core.component.orders.domain.entities.Order;

public interface OrderRequestRepository {
    void save (Order order);
}