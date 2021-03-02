package com.vas.aos.core.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public record CreateOrderDTO(@JsonProperty("customerName") String customerName,
                             @JsonProperty("products") List<ProductDTO> products,
                             @JsonProperty("payment") PaymentDTO payment) {

    public CreateOrderDTO(String customerName, PaymentDTO payment) {
        this(customerName, new ArrayList<>(), payment);
    }

    public void addProduct(ProductDTO p) {
        products.add(p);
    }
}
