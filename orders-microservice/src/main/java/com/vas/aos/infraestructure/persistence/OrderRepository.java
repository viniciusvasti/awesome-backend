package com.vas.aos.infraestructure.persistence;

import com.vas.aos.core.component.orders.application.repository.OrderRequestRepository;
import com.vas.aos.core.component.orders.domain.entities.Order;
import com.vas.aos.infraestructure.persistence.models.OrderJpaRelModel;
import com.vas.aos.infraestructure.persistence.models.PaymentJpaRelModel;
import com.vas.aos.infraestructure.persistence.models.ProductJpaRelModel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class OrderRepository implements OrderRequestRepository {

    private final OrderMySQLRepository repository;

    @Override
    public void save(Order order) {
        OrderJpaRelModel orderModel = new OrderJpaRelModel(
                order.getId(),
                order.getCustomerName(),
                new ArrayList<>(),
                new PaymentJpaRelModel(order.getPayment().getPaymentMethod()));
        order.getProducts().forEach(
                product -> orderModel.getProducts().add(
                        new ProductJpaRelModel(product.getSku(), product.getName(),
                                product.getPrice(), orderModel)));
        repository.save(orderModel);
    }
}