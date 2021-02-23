package com.vas.aos.core.component.orders.domain;

import com.vas.aos.core.component.orders.domain.entities.Order;
import com.vas.aos.core.component.orders.domain.entities.Payment;
import com.vas.aos.core.component.orders.domain.entities.Product;

import java.util.List;
import java.util.UUID;

public class OrderFactoryImpl implements OrderFactory {
    @Override
    public Order create(String customerName, List<Product> products, Payment payment) {
        Order order = new Order(UUID.randomUUID(), customerName, payment, false, false);
        products.forEach(p -> order.addProduct(p));
        return order;
    }
}