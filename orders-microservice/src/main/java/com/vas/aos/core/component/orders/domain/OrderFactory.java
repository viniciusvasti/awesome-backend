package com.vas.aos.core.component.orders.domain;

import com.vas.aos.core.component.orders.domain.entities.Order;
import com.vas.aos.core.component.orders.domain.entities.Payment;
import com.vas.aos.core.component.orders.domain.entities.Product;

import java.util.List;

public interface OrderFactory {
    Order create(String customerName, List<Product> products, Payment payment);
}