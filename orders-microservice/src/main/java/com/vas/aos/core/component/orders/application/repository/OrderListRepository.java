package com.vas.aos.core.component.orders.application.repository;

import com.vas.aos.core.component.orders.domain.entities.Order;

import java.util.List;

public interface OrderListRepository {
    List<Order> findAll ();
}