package com.vas.aos.core.component.orders.application.service;

import com.vas.aos.core.component.orders.application.dtos.CreateOrderDTO;
import com.vas.aos.core.component.orders.application.dtos.CreatedOrderDTO;
import com.vas.aos.core.component.orders.application.exceptions.InvalidOrderRequestException;
import com.vas.aos.core.component.orders.application.pubsub.OrderReceivedPublisher;
import com.vas.aos.core.component.orders.application.dtos.event.OrderReceivedEventDTO;
import com.vas.aos.core.component.orders.application.repository.OrderRequestRepository;
import com.vas.aos.core.component.orders.domain.OrderFactory;
import com.vas.aos.core.component.orders.domain.entities.Order;
import com.vas.aos.core.component.orders.domain.entities.Payment;
import com.vas.aos.core.component.orders.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class OrderRequestServiceImpl implements OrderRequestService {

    private final OrderRequestRepository orderRequestRepository;
    private final OrderFactory orderFactory;
    private final OrderReceivedPublisher orderReceivedPublisher;

    // TODO create utility class for mapping objects
    @Override
    public CreatedOrderDTO create(CreateOrderDTO createOrderDTO) {
        log.info("Createing order: {}", createOrderDTO);
        Order order = mapCreateOrderDTOToOrder(createOrderDTO);
        validateOrder(order);
        orderRequestRepository.save(order);
        CreatedOrderDTO orderCreated = new CreatedOrderDTO(order.getId());
        // TODO use Spring Events for publishing event messages
        publishOrderReceivedEvent(order);
        return orderCreated;
    }

    private void publishOrderReceivedEvent(Order order) {
        orderReceivedPublisher.publish(OrderReceivedEventDTO.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .payment(new OrderReceivedEventDTO.Payment(order.getPayment().getPaymentMethod()))
                .products(
                        order.getProducts().stream().map(
                                p -> new OrderReceivedEventDTO.Product(p.getSku(), p.getName(), p.getPrice())
                        ).collect(Collectors.toList()))
                .build());
    }

    private Order mapCreateOrderDTOToOrder(CreateOrderDTO createOrderDTO) {
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
        return order;
    }

    private void validateOrder(Order order) {
        // TODO throw exception containing all errors, instead of throwing after the first one
        if (!order.hasProducts()) {
            throw new InvalidOrderRequestException("Order has to have products.");
        }
        if (!order.hasPayment()) {
            throw new InvalidOrderRequestException("Order has to have a payment.");
        }
    }
}