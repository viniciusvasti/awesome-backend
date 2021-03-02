package com.vas.aos.infrastructure.persistence;

import com.vas.aos.core.domain.entities.*;
import com.vas.aos.infrastructure.persistence.models.OrderJpaRelModel;
import com.vas.aos.infrastructure.persistence.models.PaymentJpaRelModel;
import com.vas.aos.infrastructure.persistence.models.ProductJpaRelModel;
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
public class OrderCommandRepositoryTest {

    @Mock
    private OrderJPARepository orderMySQLRepository;
    private OrderCommandRepository orderCommandRepository;

    @BeforeEach
    void initService() {
        orderCommandRepository = new OrderCommandRepository(orderMySQLRepository);
    }

    @Test
    void givenCreateOrder_whenSaving_thenReturnCreatedOrder() {
        var id = UUID.randomUUID();
        Order order = new Order(id, "Vinicius", new Payment(PaymentMethod.CREDIT),
                ProcessStatus.PENDING, ProcessStatus.PENDING);
        order.addProduct(new Product(1234, "Batteries", 5));

        OrderJpaRelModel orderJpaRelModel = new OrderJpaRelModel(id, "Vinicius", new ArrayList<>(),
                new PaymentJpaRelModel(PaymentMethod.CREDIT));
        orderJpaRelModel.getProducts().add(new ProductJpaRelModel(UUID.randomUUID(), 1234, "Batteries", 5,
                orderJpaRelModel));
        given(orderMySQLRepository.save(any(OrderJpaRelModel.class))).willReturn(orderJpaRelModel);

        assertDoesNotThrow(() -> orderCommandRepository.save(order));
        verify(orderMySQLRepository, times(1)).save(any(OrderJpaRelModel.class));
    }
}
