package com.vas.aos.core.component.stock.application.service;

import com.vas.aos.core.component.stock.application.dtos.event.OrderReceivedEventDTO;

import java.util.List;

public interface CheckProductStockService {
    void reserveProducts(List<OrderReceivedEventDTO.Product> products);
}
