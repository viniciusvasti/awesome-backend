package com.vas.aos.infrastructure.persistence;

import com.vas.aos.core.domain.entities.Order;
import com.vas.aos.core.domain.entities.Payment;
import com.vas.aos.core.domain.entities.Product;
import com.vas.aos.infrastructure.persistence.models.OrderJpaRelModel;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class GetOrderRepository implements com.vas.aos.core.application.repository.GetOrderRepository {
    private final OrderJPARepository repository;

    @Transactional
    @Override
    public Optional<Order> getById(final UUID id) {
        return repository.findById(id)
                .map(orderJpaRelModel -> mapToOrder(orderJpaRelModel));
    }

    private Order mapToOrder(final OrderJpaRelModel orderJpaRelModel) {
        Order order = new Order(orderJpaRelModel.getId(),
                orderJpaRelModel.getCustomerName(),
                new Payment(orderJpaRelModel.getPayment().getPaymentMethod()),
                orderJpaRelModel.getPaymentProcessed(),
                orderJpaRelModel.getStockInventoryChecked());
        orderJpaRelModel.getProducts().forEach(
                productJpaRelModel -> order.getProducts().add(
                        new Product(productJpaRelModel.getSku(),
                                productJpaRelModel.getName(), productJpaRelModel.getPrice())
                )
        );
        return order;
    }
}
