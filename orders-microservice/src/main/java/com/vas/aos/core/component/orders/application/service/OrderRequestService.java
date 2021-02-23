package com.vas.aos.core.component.orders.application.service;

import com.vas.aos.core.component.orders.application.dtos.CreateOrderDTO;
import com.vas.aos.core.component.orders.application.dtos.CreatedOrderDTO;

public interface OrderRequestService {
    CreatedOrderDTO create(CreateOrderDTO createOrderDTO);
}