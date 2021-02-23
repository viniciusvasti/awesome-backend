package com.vas.aos.core.component.orders.application.service;

import com.vas.aos.core.component.orders.application.dtos.OrderListItemDTO;

import java.util.List;

public interface OrdersListService {
    List<OrderListItemDTO> listAll();
}