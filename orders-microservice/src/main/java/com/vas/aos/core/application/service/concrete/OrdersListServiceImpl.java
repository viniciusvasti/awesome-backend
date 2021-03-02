package com.vas.aos.core.application.service.concrete;

import com.vas.aos.core.application.dtos.OrderListItemDTO;
import com.vas.aos.core.application.dtos.PaymentDTO;
import com.vas.aos.core.application.dtos.ProductDTO;
import com.vas.aos.core.application.repository.OrderListRepository;
import com.vas.aos.core.application.service.OrdersListService;
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
                        o.getStockInventoryChecked(), o.getPaymentProcessed())).collect(Collectors.toList());
    }
}
