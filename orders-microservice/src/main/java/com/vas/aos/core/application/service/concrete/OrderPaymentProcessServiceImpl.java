package com.vas.aos.core.application.service.concrete;

import com.vas.aos.core.application.repository.OrderRequestRepository;
import com.vas.aos.core.application.service.OrderPaymentProcessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class OrderPaymentProcessServiceImpl implements OrderPaymentProcessService {
    private final OrderRequestRepository orderRequestRepository;

    @Override
    public void markAsPaymentAsProcessed(final UUID id) {
        log.info("Marking as products reserved: {}", id);
        orderRequestRepository.markAsPaymentAsProcessed(id);
    }
}
