package com.vas.aos.infraestructure.persistence;

import com.vas.aos.core.component.orders.application.repository.OrderRequestRepository;
import com.vas.aos.core.component.orders.domain.entities.Order;
import com.vas.aos.infraestructure.persistence.models.OrderJpaRelModel;
import com.vas.aos.infraestructure.persistence.models.PaymentJpaRelModel;
import com.vas.aos.infraestructure.persistence.models.ProductJpaRelModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class OrderCommandRepository implements OrderRequestRepository {

    private final OrderMySQLRepository repository;

    @Override
    public void save(Order order) {
        log.info("Persisting {}", order);
        OrderJpaRelModel orderModel = mapOrderToOrderJpaRelModel(order);
        repository.save(orderModel);
        log.info("Success while persisting");
    }

    private OrderJpaRelModel mapOrderToOrderJpaRelModel(Order order) {
        OrderJpaRelModel orderModel = new OrderJpaRelModel(
                order.getId(),
                order.getCustomerName(),
                new ArrayList<>(),
                new PaymentJpaRelModel(order.getPayment().getPaymentMethod()));
        order.getProducts().forEach(
                product -> orderModel.getProducts().add(
                        new ProductJpaRelModel(UUID.randomUUID(), product.getSku(), product.getName(),
                                product.getPrice(), orderModel)));
        return orderModel;
    }
}