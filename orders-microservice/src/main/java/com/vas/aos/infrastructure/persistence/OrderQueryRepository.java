package com.vas.aos.infrastructure.persistence;

import com.vas.aos.core.application.repository.OrderListRepository;
import com.vas.aos.core.domain.entities.Order;
import com.vas.aos.core.domain.entities.Payment;
import com.vas.aos.core.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class OrderQueryRepository implements OrderListRepository {

    private final OrderJPARepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll().stream().map(
                o -> {
                    Order order = new Order(o.getId(),
                            o.getCustomerName(),
                            new Payment(o.getPayment().getPaymentMethod()),
                            o.getPaymentProcessed(),
                            o.getStockInventoryChecked());
                    o.getProducts().forEach(p -> order.addProduct(new Product(p.getSku(),
                            p.getName(), p.getPrice())));
                    return order;
                }).collect(Collectors.toList());
    }
}
