package com.yeoriggun.yeoriggun.dto.order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    private Long userId;
    private Long storeId;
    private List<OrderItemRequestDto> items;
}