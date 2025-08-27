package com.yeoriggun.yeoriggun.service.store;

import com.yeoriggun.yeoriggun.dto.store.ProductDto;
import com.yeoriggun.yeoriggun.entity.store.Product;
import com.yeoriggun.yeoriggun.repository.store.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //가게별 상품 전체 리스트
    public List<ProductDto> getProductsByStoreId(Long storeId) {
        List<Product> products = productRepository.findByStoreId(storeId);
        return products.stream()
                .map(p -> ProductDto.builder()
                        .productId(p.getProductId())
                        .name(p.getName())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .stock(p.getStock())
                        .imageUrl(p.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

    //상품 수정
    public ProductDto updateProduct(Long productId, Double price, Integer stock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("상품을 찾을 수 없습니다."));

        // 수정
        product.setPrice(price);
        product.setStock(stock);

        // 저장
        Product updated = productRepository.save(product);

        // DTO 변환
        return ProductDto.builder()
                .productId(updated.getProductId())
                .name(updated.getName())
                .description(updated.getDescription())
                .price(updated.getPrice())
                .stock(updated.getStock())
                .imageUrl(updated.getImageUrl())
                .build();
    }

    //상품 등록
    public ProductDto createProduct(ProductDto productDto, Long storeId) {
        Product product = Product.builder()
                .storeId(storeId)
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .imageUrl(productDto.getImageUrl())
                .build();

        Product saved = productRepository.save(product);

        return ProductDto.builder()
                .productId(saved.getProductId())
                .name(saved.getName())
                .description(saved.getDescription())
                .price(saved.getPrice())
                .stock(saved.getStock())
                .imageUrl(saved.getImageUrl())
                .build();
    }
}