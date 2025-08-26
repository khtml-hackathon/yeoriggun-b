package com.yeoriggun.yeoriggun.dto.order;

public record OrderItemInfoDto(
        String productName,
        int quantity,
        Double price
) {}
