package com.vas.aos.presentation.api;

import com.vas.aos.core.component.orders.application.dtos.CreateOrderDTO;
import com.vas.aos.core.component.orders.application.dtos.CreatedOrderDTO;
import com.vas.aos.core.component.orders.application.service.OrderRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order-management/orders")
@AllArgsConstructor
public class OrderCommandController {

    private final OrderRequestService orderRequestService;

    @PostMapping
    public ResponseEntity<CreatedOrderDTO> requestOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        CreatedOrderDTO createdOrderDTO = orderRequestService.create(createOrderDTO);
        return new ResponseEntity<>(createdOrderDTO, HttpStatus.CREATED);
    }

}