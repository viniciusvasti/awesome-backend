package com.vas.aos.core.application.service.concrete;

import com.vas.aos.core.application.exceptions.OrderNotFoundException;
import com.vas.aos.core.application.repository.GetOrderRepository;
import com.vas.aos.core.application.service.GetOrderService;
import com.vas.aos.core.domain.entities.Order;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class GetOrderServiceImpl implements GetOrderService {
    private final GetOrderRepository getOrderRepository;

    @Override
    public Order getById(final UUID id) {
        return getOrderRepository.getById(id).orElseThrow(() -> new OrderNotFoundException("Order" +
                " not found"));
    }
}
