package com.yeoriggun.yeoriggun.dto.order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private Long orderId;
    private Long userId;
    private Long storeId;
    private Double totalPrice;
    private String status;
    private List<OrderItemResponseDto> items;
}