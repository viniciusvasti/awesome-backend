package com.vas.aos.core.application.repository;

import com.vas.aos.core.domain.entities.Order;

import java.util.UUID;

public interface OrderRequestRepository {
    void save(Order order);

    void markAsProductsReserved(UUID id);

    void markAsPaymentAsProcessed(UUID id);
}
