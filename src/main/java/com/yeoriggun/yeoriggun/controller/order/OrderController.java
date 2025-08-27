package com.yeoriggun.yeoriggun.controller.order;

import com.yeoriggun.yeoriggun.dto.order.OrderInfoDto;
import com.yeoriggun.yeoriggun.dto.order.OrderRequestDto;
import com.yeoriggun.yeoriggun.dto.order.OrderResponseDto;
import com.yeoriggun.yeoriggun.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "주문", description = "주문 관련 API")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 등록", description = "상품을 주문합니다.")
    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @Operation(summary = "주문 내역 ", description = "특정 id의 주문 내역을 가져옵니다.")
    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @Operation(summary = "주문 취소 ", description = "특정 id의 주문을 취소합니다.")
    @PutMapping("/{orderId}/cancel")
    public OrderResponseDto cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }
    
    @Operation(summary = "유저별 주문 내역 리스트", description = "특정 유저의 주문 내역 리스트를 가져옵니다.")
    @GetMapping("/user/{userId}/orderList")
    public List<OrderInfoDto> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }
}