package com.vas.aos.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
// TODO use builder
public class Order {
    private final UUID id;
    private final String customerName;
    private final List<Product> products = new ArrayList<>();
    private final Payment payment;
    private ProcessStatus paymentProcessed;
    private ProcessStatus stockInventoryChecked;

    public void addProduct(Product p) {
        products.add(p);
    }

    public boolean hasProducts() {
        return !CollectionUtils.isEmpty(products);
    }

    public boolean hasPayment() {
        return payment != null;
    }
}
