package com.vas.aos.core.application.service;

import java.util.UUID;

public interface OrderPaymentProcessService {
    void markAsPaymentAsProcessed(UUID id);
}
