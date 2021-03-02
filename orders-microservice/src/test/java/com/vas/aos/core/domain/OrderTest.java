package com.vas.aos.core.domain;

import com.vas.aos.core.domain.entities.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void givenAnOrderWithProducts_whenHasProducts_thenIsTrue() {
        Order order = new Order(UUID.randomUUID(), "Vinicius", new Payment(PaymentMethod.CREDIT),
                ProcessStatus.PENDING, ProcessStatus.PENDING);
        order.addProduct(new Product(123, "Batteries", 5.0));
        assertThat(order.hasProducts()).isTrue();
    }

    @Test
    void givenAnOrderWithoutProducts_whenHasProducts_thenIsFalse() {
        Order order = new Order(UUID.randomUUID(), "Vinicius", new Payment(PaymentMethod.CREDIT),
                ProcessStatus.PENDING, ProcessStatus.PENDING);
        assertThat(order.hasProducts()).isFalse();
    }

    @Test
    void givenAnOrderWithPayment_whenHasPayment_thenIsTrue() {
        Order order = new Order(UUID.randomUUID(), "Vinicius", new Payment(PaymentMethod.CREDIT),
                ProcessStatus.PENDING, ProcessStatus.PENDING);
        assertThat(order.hasPayment()).isTrue();
    }

    @Test
    void givenAnOrderWithoutPayment_whenHasPayment_thenIsFalse() {
        Order order = new Order(UUID.randomUUID(), "Vinicius", null,
                ProcessStatus.PENDING, ProcessStatus.PENDING);
        assertThat(order.hasPayment()).isFalse();
    }
}
