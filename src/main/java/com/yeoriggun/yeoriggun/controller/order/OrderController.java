package com.yeoriggun.yeoriggun.controller.order;

import com.yeoriggun.yeoriggun.dto.order.OrderInfoDto;
import com.yeoriggun.yeoriggun.dto.order.OrderRequestDto;
import com.yeoriggun.yeoriggun.dto.order.OrderResponseDto;
import com.yeoriggun.yeoriggun.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/{orderId}/cancel")
    public OrderResponseDto cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }

    @GetMapping("/user/{userId}/orderList")
    public List<OrderInfoDto> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }
}