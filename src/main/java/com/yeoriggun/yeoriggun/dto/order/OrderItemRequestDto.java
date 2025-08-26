package com.yeoriggun.yeoriggun.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequestDto {
    private Long productId;
    private Integer quantity;
    private Double price; // 단가
}