package com.yeoriggun.yeoriggun.controller.store;

import com.yeoriggun.yeoriggun.dto.store.ProductDto;
import com.yeoriggun.yeoriggun.service.store.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "상품", description = "상품 등록,삭제,수정 관련 API")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "상품 목록", description = "상품 목록 리스트를 가져옵니다.")
    @GetMapping("/store/{storeId}")
    public List<ProductDto> getProductsByStore(@PathVariable Long storeId) {
        return productService.getProductsByStoreId(storeId);
    }

    @Operation(summary = "상품 수정", description = "등록된 상품을 수정합니다.")
    @PutMapping("/update/{productId}")
    public ProductDto updateProduct(
            @PathVariable Long productId,
            @RequestParam Double price,
            @RequestParam Integer stock) {
        return productService.updateProduct(productId, price, stock);
    }

    @Operation(summary = "상품 추가", description = "상품을 추가합니다.")
    @PostMapping("/store/{storeId}")
    public ProductDto createProduct(
            @PathVariable Long storeId,
            @RequestBody ProductDto productDto) {
        return productService.createProduct(productDto, storeId);
    }
}
