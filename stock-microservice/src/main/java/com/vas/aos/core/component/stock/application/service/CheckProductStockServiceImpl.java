package com.vas.aos.core.component.stock.application.service;

import com.vas.aos.core.component.stock.application.dtos.event.OrderReceivedEventDTO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CheckProductStockServiceImpl implements CheckProductStockService {

    @Override
    public void reserveProducts(List<OrderReceivedEventDTO.Product> products) {
      System.out.println("reserveProducts");
        // products.forEach(p -> {
        //     Product product = productListRepository.findBySku(p.getSku());
        //     if (product == null) {
        //         throw new NotEnoughProductsException("Product with sku " + p.getSku() + " not found");
        //     }
        //     if (product.getInventoryAmount() < p.getAmount()) {
        //         throw new NotEnoughProductsException("Product with sku " + p.getSku() + " has only " +
        //                 product.getInventoryAmount() + " units available");
        //     }
        //     product.setInventoryAmount(product.getInventoryAmount() - p.getAmount());
        //     productListRepository.save(product);
        // });
    }
    // public List<ProductDTO> listAll() {
    //     return productListRepository.findAll().stream().map(p -> new ProductDTO(p.getSku(),
    //             p.getName(), p.getPrice(), p.getInventoryAmount())).collect(Collectors.toList());
    // }

    // @Override
    // public List<ProductDTO> listAllAvailable() {
    //     return productListRepository.findAllAvailable().stream().map(p -> new ProductDTO(p.getSku(),
    //             p.getName(), p.getPrice(), p.getInventoryAmount())).collect(Collectors.toList());
    // }
}