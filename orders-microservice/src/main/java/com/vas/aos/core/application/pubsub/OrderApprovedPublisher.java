package com.vas.aos.core.application.pubsub;

import com.vas.aos.core.application.dtos.events.OrderApprovedEventDTO;

public interface OrderApprovedPublisher {
    void publish(OrderApprovedEventDTO event);
}
