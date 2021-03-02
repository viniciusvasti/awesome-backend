package com.vas.aos.core.domain;

import com.vas.aos.core.domain.entities.Order;
import com.vas.aos.core.domain.entities.Payment;
import com.vas.aos.core.domain.entities.Product;

import java.util.List;

public interface OrderFactory {
    Order create(String customerName, List<Product> products, Payment payment);
}
