package com.yeoriggun.yeoriggun.dto.order;

import java.util.List;

public record OrderInfoDto(
        Long orderId,
        String storeName,
        String status,
        List<OrderItemInfoDto> items
) {}