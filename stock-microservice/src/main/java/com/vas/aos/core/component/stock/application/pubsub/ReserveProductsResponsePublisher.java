package com.vas.aos.core.component.stock.application.pubsub;

import com.vas.aos.core.component.stock.application.dtos.event.OrderReceivedEventDTO;

public interface ReserveProductsResponsePublisher {
    void publish(OrderReceivedEventDTO event);
}
