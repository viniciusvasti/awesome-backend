package com.vas.aos.presentation.api;

import com.vas.aos.core.application.dtos.OrderListItemDTO;
import com.vas.aos.core.application.service.OrdersListService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/order-management/orders")
@AllArgsConstructor
public class OrderQueryController {

    private final OrdersListService ordersListService;

    @GetMapping
    public ResponseEntity<List<OrderListItemDTO>> listAll() {
        return new ResponseEntity<>(ordersListService.listAll(), HttpStatus.OK);
    }

}
