package com.vas.aos.infraestructure.persistence;

import com.vas.aos.core.component.orders.domain.entities.Order;
import com.vas.aos.core.component.orders.domain.entities.Payment;
import com.vas.aos.core.component.orders.domain.entities.PaymentMethod;
import com.vas.aos.core.component.orders.domain.entities.Product;
import com.vas.aos.infraestructure.persistence.models.OrderJpaRelModel;
import com.vas.aos.infraestructure.persistence.models.PaymentJpaRelModel;
import com.vas.aos.infraestructure.persistence.models.ProductJpaRelModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryTest {

    @Mock
    private OrderMySQLRepository orderMySQLRepository;
    private OrderRepository orderRepository;

    @BeforeEach
    void initService() {
        orderRepository = new OrderRepository(orderMySQLRepository);
    }

    @Test
    void givenCreateOrder_whenSaving_thenReturnCreatedOrder() {
        var id = UUID.randomUUID();
        Order order = new Order(id, "Vinicius", new Payment(PaymentMethod.CREDIT));
        order.addProduct(new Product(1234, "Batteries", 5));

        OrderJpaRelModel orderJpaRelModel = new OrderJpaRelModel(id, "Vinicius", new ArrayList<>(),
                new PaymentJpaRelModel(PaymentMethod.CREDIT));
        orderJpaRelModel.getProducts().add(new ProductJpaRelModel(1234, "Batteries", 5, orderJpaRelModel));
        given(orderMySQLRepository.save(any(OrderJpaRelModel.class))).willReturn(orderJpaRelModel);

        assertDoesNotThrow(() -> orderRepository.save(order));
        verify(orderMySQLRepository, times(1)).save(any(OrderJpaRelModel.class));
    }
}