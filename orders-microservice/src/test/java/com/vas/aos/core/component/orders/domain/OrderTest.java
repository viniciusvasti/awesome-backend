package com.vas.aos.core.component.orders.domain;

import com.vas.aos.core.component.orders.domain.entities.Order;
import com.vas.aos.core.component.orders.domain.entities.Payment;
import com.vas.aos.core.component.orders.domain.entities.PaymentMethod;
import com.vas.aos.core.component.orders.domain.entities.Product;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void givenAnOrderWithProducts_whenHasProducts_thenIsTrue() {
        Order order = new Order(UUID.randomUUID(), "Vinicius", new Payment(PaymentMethod.CREDIT));
        order.addProduct(new Product(123, "Batteries", 5.0));
        assertThat(order.hasProducts()).isTrue();
    }

    @Test
    void givenAnOrderWithoutProducts_whenHasProducts_thenIsFalse() {
        Order order = new Order(UUID.randomUUID(), "Vinicius", new Payment(PaymentMethod.CREDIT));
        assertThat(order.hasProducts()).isFalse();
        Order order2 = new Order(UUID.randomUUID(), "Vinicius", new Payment(PaymentMethod.CREDIT));
        assertThat(order2.hasProducts()).isFalse();
    }

    @Test
    void givenAnOrderWithPayment_whenHasPayment_thenIsTrue() {
        Order order = new Order(UUID.randomUUID(), "Vinicius", new Payment(PaymentMethod.CREDIT));
        assertThat(order.hasPayment()).isTrue();
    }

    @Test
    void givenAnOrderWithoutPayment_whenHasPayment_thenIsFalse() {
        Order order = new Order(UUID.randomUUID(), "Vinicius", null);
        assertThat(order.hasPayment()).isFalse();
    }
}