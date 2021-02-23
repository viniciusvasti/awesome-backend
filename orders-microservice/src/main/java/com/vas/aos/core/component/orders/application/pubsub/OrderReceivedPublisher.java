package com.vas.aos.core.component.orders.application.pubsub;

import com.vas.aos.core.component.orders.application.dtos.event.OrderReceivedEventDTO;

public interface OrderReceivedPublisher {
    void publish(OrderReceivedEventDTO event);
}