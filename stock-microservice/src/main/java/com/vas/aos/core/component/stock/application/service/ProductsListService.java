package com.vas.aos.core.component.stock.application.service;

import com.vas.aos.core.component.stock.application.dtos.ProductDTO;

import java.util.List;

public interface ProductsListService {
    List<ProductDTO> listAll();

    List<ProductDTO> listAllAvailable();
}