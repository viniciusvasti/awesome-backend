package com.vas.aos.core.component.stock.application.service;

import com.vas.aos.core.component.stock.application.dtos.ProductDTO;
import com.vas.aos.core.component.stock.application.repository.ProductListRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductsListServiceImpl implements ProductsListService {
    private final ProductListRepository productListRepository;

    @Override
    public List<ProductDTO> listAll() {
        return productListRepository.findAll().stream().map(p -> new ProductDTO(p.getSku(),
                p.getName(), p.getPrice(), p.getInventoryAmount())).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> listAllAvailable() {
        return productListRepository.findAllAvailable().stream().map(p -> new ProductDTO(p.getSku(),
                p.getName(), p.getPrice(), p.getInventoryAmount())).collect(Collectors.toList());
    }
}