package com.vas.aos.infrastructure.persistence;

import com.vas.aos.core.application.repository.OrderRequestRepository;
import com.vas.aos.core.domain.entities.Order;
import com.vas.aos.core.domain.entities.ProcessStatus;
import com.vas.aos.infrastructure.persistence.models.OrderJpaRelModel;
import com.vas.aos.infrastructure.persistence.models.PaymentJpaRelModel;
import com.vas.aos.infrastructure.persistence.models.ProductJpaRelModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class OrderCommandRepository implements OrderRequestRepository {

    private final OrderJPARepository repository;

    @Override
    public void save(Order order) {
        log.info("Persisting {}", order);
        OrderJpaRelModel orderModel = mapOrderToOrderJpaRelModel(order);
        repository.save(orderModel);
        log.info("Success while persisting");
    }

    // I had to add "@Transaction" here for not throwing LazyInitializationException
    @Override
    @Transactional
    public void markAsProductsReserved(final UUID id) {
        OrderJpaRelModel orderJpaRelModel = repository.getOne(id);
        orderJpaRelModel.setStockInventoryChecked(ProcessStatus.SUCCEEDED);
        orderJpaRelModel.setStockInventoryCheckedAt(LocalDateTime.now());
        repository.save(orderJpaRelModel);
    }

    // I had to add "@Transaction" here for not throwing LazyInitializationException
    @Override
    @Transactional
    public void markAsPaymentAsProcessed(final UUID id) {
        OrderJpaRelModel orderJpaRelModel = repository.getOne(id);
        orderJpaRelModel.setPaymentProcessed(ProcessStatus.SUCCEEDED);
        orderJpaRelModel.setPaymentProcessedAt(LocalDateTime.now());
        repository.save(orderJpaRelModel);
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
