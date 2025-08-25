package com.yeoriggun.yeoriggun.dto.store;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long productId;

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private String imageUrl;
}