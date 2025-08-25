package com.yeoriggun.yeoriggun.controller.store;

import com.yeoriggun.yeoriggun.dto.store.ProductDto;
import com.yeoriggun.yeoriggun.service.store.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/store/{storeId}")
    public List<ProductDto> getProductsByStore(@PathVariable Long storeId) {
        return productService.getProductsByStoreId(storeId);
    }
}
