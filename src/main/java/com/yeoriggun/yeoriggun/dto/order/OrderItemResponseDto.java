package com.yeoriggun.yeoriggun.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDto {
    private Long orderItemId;
    private Long productId;
    private Integer quantity;
    private Double price;
}