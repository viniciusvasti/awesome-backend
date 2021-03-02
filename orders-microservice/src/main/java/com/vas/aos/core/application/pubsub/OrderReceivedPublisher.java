package com.vas.aos.core.application.pubsub;

import com.vas.aos.core.application.dtos.events.OrderReceivedEventDTO;

public interface OrderReceivedPublisher {
    void publish(OrderReceivedEventDTO event);
}
