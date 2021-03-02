package com.vas.aos.core.application.repository;

import com.vas.aos.core.domain.entities.Order;

import java.util.List;

public interface OrderListRepository {
    List<Order> findAll();
}
