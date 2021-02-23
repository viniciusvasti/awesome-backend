package com.vas.aos.core.component.orders.application.service;

import com.vas.aos.core.component.orders.application.dtos.CreateOrderDTO;
import com.vas.aos.core.component.orders.application.dtos.CreatedOrderDTO;
import com.vas.aos.core.component.orders.application.dtos.PaymentDTO;
import com.vas.aos.core.component.orders.application.dtos.ProductDTO;
import com.vas.aos.core.component.orders.application.exceptions.InvalidOrderRequestException;
import com.vas.aos.core.component.orders.application.repository.OrderRequestRepository;
import com.vas.aos.core.component.orders.domain.OrderFactory;
import com.vas.aos.core.component.orders.domain.OrderFactoryImpl;
import com.vas.aos.core.component.orders.domain.entities.Order;
import com.vas.aos.core.component.orders.domain.entities.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
public class OrderRequestServiceImplTest {

    @Mock
    private OrderRequestRepository orderRequestRepository;
    private OrderFactory orderFactory;
    private OrderRequestService orderRequestService;

    @BeforeEach
    void initService() {
        orderFactory = new OrderFactoryImpl();
        orderRequestService =
                new OrderRequestServiceImpl(orderRequestRepository, orderFactory);
    }

    @Test
    void givenValidCreateOrder_whenRequestAnOrder_thenReturnCreatedOrder() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO("Vinicius",
                new PaymentDTO(PaymentMethod.CREDIT));
        createOrderDTO.addProduct(new ProductDTO(1234, "Batteries", 5));

        willDoNothing().given(orderRequestRepository).save(any(Order.class));

        CreatedOrderDTO createdOrderDTO = orderRequestService.create(createOrderDTO);
        assertThat(createdOrderDTO).isNotNull();
        assertThat(createdOrderDTO.id()).isNotNull();
    }

    @Test
    void givenCreateOrderWithoutProducts_whenRequestAnOrder_thenThrow() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO("Vinicius",
                new PaymentDTO(PaymentMethod.CREDIT));

        assertThatThrownBy(() -> orderRequestService.create(createOrderDTO))
                .isInstanceOf(InvalidOrderRequestException.class)
                .hasMessage("Order has to have products.");
    }

    @Test
    void givenCreateOrderWithoutPayment_whenRequestAnOrder_thenThrow() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO("Vinicius", null);
        createOrderDTO.addProduct(new ProductDTO(1234, "Batteries", 5));

        assertThatThrownBy(() -> orderRequestService.create(createOrderDTO))
                .isInstanceOf(InvalidOrderRequestException.class)
                .hasMessage("Order has to have a payment.");
    }

}