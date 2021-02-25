package com.vas.aos.presentation.api;

import com.vas.aos.core.component.stock.application.dtos.ProductDTO;
import com.vas.aos.core.component.stock.application.service.ProductsListService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/stock-management/products")
@AllArgsConstructor
public class ProductQueryController {

    private final ProductsListService productsListService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listAll(@RequestParam(name = "only-available",
            required = false) Optional<Boolean> onlyAvailable) {
        if (onlyAvailable.isEmpty())
            return new ResponseEntity<>(productsListService.listAll(), HttpStatus.OK);
        return  new ResponseEntity<>(productsListService.listAllAvailable(),
                HttpStatus.OK);
    }

}