package com.vas.aos.core.application.service;

import com.vas.aos.core.application.dtos.OrderListItemDTO;

import java.util.List;

public interface OrdersListService {
    List<OrderListItemDTO> listAll();
}
