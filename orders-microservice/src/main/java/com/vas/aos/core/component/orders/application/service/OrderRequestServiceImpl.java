package com.vas.aos.core.component.orders.application.service;

import com.vas.aos.core.component.orders.application.dtos.CreateOrderDTO;
import com.vas.aos.core.component.orders.application.dtos.CreatedOrderDTO;
import com.vas.aos.core.component.orders.application.exceptions.InvalidOrderRequestException;
import com.vas.aos.core.component.orders.application.repository.OrderRequestRepository;
import com.vas.aos.core.component.orders.domain.entities.Order;
import com.vas.aos.core.component.orders.domain.OrderFactory;
import com.vas.aos.core.component.orders.domain.entities.Payment;
import com.vas.aos.core.component.orders.domain.entities.Product;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderRequestServiceImpl implements OrderRequestService {

    final OrderRequestRepository orderRequestRepository;
    final OrderFactory orderFactory;

    @Override
    public CreatedOrderDTO create(CreateOrderDTO createOrderDTO) {
        List<Product> products = createOrderDTO.products().stream()
                .map(productDTO -> new Product(
                        productDTO.sku(),
                        productDTO.name(),
                        productDTO.price()))
                .collect(Collectors.toList());
        Payment payment =
                createOrderDTO.payment() != null ?
                        new Payment(createOrderDTO.payment().paymentMethod()) : null;
        Order order = orderFactory.create(createOrderDTO.customerName(), products,
                payment);
        validateOrder(order, products);
        orderRequestRepository.save(order);
        CreatedOrderDTO orderCreated = new CreatedOrderDTO(order.getId());
        return orderCreated;
    }

    private void validateOrder(Order order, List<Product> products) {
        // TODO throw exception containing all errors, instead of throwing after the first one
        if (!order.hasProducts()) {
            throw new InvalidOrderRequestException("Order has to have products.");
        }
        if (!order.hasPayment()) {
            throw new InvalidOrderRequestException("Order has to have a payment.");
        }
    }
}