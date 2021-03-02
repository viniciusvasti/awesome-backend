package com.vas.aos.core.application.service.concrete;

import com.vas.aos.core.application.dtos.events.OrderProcessPaymentCommandDTO;
import com.vas.aos.core.application.pubsub.RequestPaymentPublisher;
import com.vas.aos.core.application.service.GetOrderService;
import com.vas.aos.core.application.service.RequestOrderPaymentService;
import com.vas.aos.core.domain.entities.Order;
import com.vas.aos.core.domain.entities.PaymentMethod;
import com.vas.aos.core.domain.entities.Product;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class RequestOrderPaymentServiceImpl implements RequestOrderPaymentService {
    private final GetOrderService getOrderService;
    private final RequestPaymentPublisher requestPaymentPublisher;

    @Override
    public void requestPayment(final UUID id) {
        Order order = getOrderService.getById(id);
        double total = order
                .getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .reduce(0, Double::sum);
        PaymentMethod paymentMethod = order.getPayment().getPaymentMethod();
        OrderProcessPaymentCommandDTO.Payment payment = new OrderProcessPaymentCommandDTO.Payment(paymentMethod, total);
        OrderProcessPaymentCommandDTO orderProcessPaymentCommand = new OrderProcessPaymentCommandDTO(order.getId(), order.getCustomerName(), payment);
        requestPaymentPublisher.publish(orderProcessPaymentCommand);
    }
}
