package com.vas.aos.core.domain;

import com.vas.aos.core.domain.entities.Order;
import com.vas.aos.core.domain.entities.Payment;
import com.vas.aos.core.domain.entities.ProcessStatus;
import com.vas.aos.core.domain.entities.Product;

import java.util.List;
import java.util.UUID;

public class OrderFactoryImpl implements OrderFactory {
    @Override
    public Order create(String customerName, List<Product> products, Payment payment) {
        Order order = new Order(UUID.randomUUID(), customerName, payment, ProcessStatus.PENDING, ProcessStatus.PENDING);
        products.forEach(order::addProduct);
        return order;
    }
}
