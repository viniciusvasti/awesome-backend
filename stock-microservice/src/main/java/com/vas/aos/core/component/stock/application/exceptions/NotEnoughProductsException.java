package com.vas.aos.core.component.stock.application.exceptions;

import java.util.List;

public class NotEnoughProductsException extends RuntimeException {
    public NotEnoughProductsException(List<Integer> skus) {
        super("Not enough stock units for products with sku: " + skus);
    }
}
