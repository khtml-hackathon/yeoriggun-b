package com.yeoriggun.yeoriggun.service.store;

import com.yeoriggun.yeoriggun.dto.store.ProductDto;
import com.yeoriggun.yeoriggun.entity.store.Product;
import com.yeoriggun.yeoriggun.repository.store.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

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
}