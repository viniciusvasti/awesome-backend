package com.vas.aos.core.application.service;

import com.vas.aos.core.application.dtos.CreateOrderDTO;
import com.vas.aos.core.application.dtos.CreatedOrderDTO;

public interface OrderRequestService {
    CreatedOrderDTO create(CreateOrderDTO createOrderDTO);
}
