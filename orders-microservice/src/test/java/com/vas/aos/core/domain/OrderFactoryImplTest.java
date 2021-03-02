package com.vas.aos.core.domain;

import com.vas.aos.core.domain.entities.Order;
import com.vas.aos.core.domain.entities.Payment;
import com.vas.aos.core.domain.entities.PaymentMethod;
import com.vas.aos.core.domain.entities.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderFactoryImplTest {

    @Test
    void givenOrderAttributes_whenCreate_thenReturnNewOrder() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(123, "Batteries", 5.0));

        OrderFactory orderFactory = new OrderFactoryImpl();
        Order order = orderFactory.create("Vinicius", products, new Payment(PaymentMethod.CREDIT));
        assertThat(order.getCustomerName()).isEqualTo("Vinicius");
        assertThat(order.hasProducts()).isTrue();
        assertThat(order.hasPayment()).isTrue();
    }
}
