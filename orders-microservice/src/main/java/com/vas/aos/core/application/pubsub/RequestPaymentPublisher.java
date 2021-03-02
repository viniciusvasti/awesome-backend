package com.vas.aos.core.application.pubsub;

import com.vas.aos.core.application.dtos.events.OrderProcessPaymentCommandDTO;

public interface RequestPaymentPublisher {
    void publish(OrderProcessPaymentCommandDTO orderProcessPaymentCommand);
}
