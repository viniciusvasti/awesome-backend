package com.vas.aos.core.component.orders.application.service;

import com.vas.aos.core.component.orders.application.dtos.OrderListItemDTO;
import com.vas.aos.core.component.orders.application.dtos.PaymentDTO;
import com.vas.aos.core.component.orders.application.dtos.ProductDTO;
import com.vas.aos.core.component.orders.application.repository.OrderListRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrdersListServiceImpl implements OrdersListService {
    private final OrderListRepository orderListRepository;

    @Override
    public List<OrderListItemDTO> listAll() {
        return orderListRepository.findAll().stream().map(
                o -> new OrderListItemDTO(
                        o.getId(),
                        o.getCustomerName(),
                        o.getProducts().stream().map(
                                p -> new ProductDTO(
                                        p.getSku(),
                                        p.getName(),
                                        p.getPrice())).collect(Collectors.toList()),
                        new PaymentDTO(o.getPayment().getPaymentMethod()),
                        o.isStockInventoryChecked(), o.isPaymentProcessed())).collect(Collectors.toList());
    }
}