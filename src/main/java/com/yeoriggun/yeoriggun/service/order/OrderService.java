package com.yeoriggun.yeoriggun.service.order;

import com.yeoriggun.yeoriggun.dto.order.*;
import com.yeoriggun.yeoriggun.entity.order.OrderItems;
import com.yeoriggun.yeoriggun.entity.order.Orders;
import com.yeoriggun.yeoriggun.entity.store.Product;
import com.yeoriggun.yeoriggun.entity.store.Store;
import com.yeoriggun.yeoriggun.repository.order.OrderItemRepository;
import com.yeoriggun.yeoriggun.repository.order.OrderRepository;
import com.yeoriggun.yeoriggun.repository.store.ProductRepository;
import com.yeoriggun.yeoriggun.repository.store.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;


    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        // 주문 총액 계산
        double totalPrice = requestDto.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        // orders 테이블 저장
        Orders order = Orders.builder()
                .userId(requestDto.getUserId())
                .storeId(requestDto.getStoreId())
                .totalPrice(totalPrice)
                .status("PENDING")
                .build();

        Orders savedOrder = orderRepository.save(order);

        // order_items 테이블 저장
        List<OrderItems> savedItems = new ArrayList<>();
        for (OrderItemRequestDto itemDto : requestDto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("상품 없음"));

            // 재고 확인
            if (product.getStock() < itemDto.getQuantity()) {
                throw new RuntimeException("재고 부족: " + product.getName());
            }

            // 재고 차감
            product.setStock(product.getStock() - itemDto.getQuantity());
            productRepository.save(product);

            OrderItems item = OrderItems.builder()
                    .order(savedOrder)
                    .productId(itemDto.getProductId())
                    .quantity(itemDto.getQuantity())
                    .price(itemDto.getPrice())
                    .build();

            savedItems.add(orderItemRepository.save(item));
        }

        // 응답 DTO 변환
        List<OrderItemResponseDto> responseItems = savedItems.stream()
                .map(i -> new OrderItemResponseDto(i.getOrderItemId(), i.getProductId(), i.getQuantity(), i.getPrice()))
                .toList();

        return OrderResponseDto.builder()
                .orderId(savedOrder.getOrderId())
                .userId(savedOrder.getUserId())
                .storeId(savedOrder.getStoreId())
                .totalPrice(savedOrder.getTotalPrice())
                .status(savedOrder.getStatus())
                .items(responseItems)
                .build();
    }

    public OrderResponseDto getOrderById(Long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문 없음"));

        List<OrderItems> items = orderItemRepository.findByOrderOrderId(orderId);

        List<OrderItemResponseDto> responseItems = items.stream()
                .map(i -> new OrderItemResponseDto(i.getOrderItemId(), i.getProductId(), i.getQuantity(), i.getPrice()))
                .toList();

        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .storeId(order.getStoreId())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .items(responseItems)
                .build();
    }

    @Transactional
    public OrderResponseDto cancelOrder(Long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문 없음"));

        if ("COMPLETED".equals(order.getStatus())) {
            throw new RuntimeException("이미 완료된 주문은 취소 불가");
        }

        // 주문 상세 조회
        List<OrderItems> items = orderItemRepository.findByOrderOrderId(orderId);

        // 재고 복원
        for (OrderItems item : items) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("상품 없음"));

            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        }

        // order_items 삭제
        orderItemRepository.deleteAll(items);

        // orders 삭제
        orderRepository.delete(order);

        // 응답 DTO 생성 (삭제했으니 상태만 CANCELLED로 표시)
        List<OrderItemResponseDto> responseItems = items.stream()
                .map(i -> new OrderItemResponseDto(i.getOrderItemId(), i.getProductId(), i.getQuantity(), i.getPrice()))
                .toList();

        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .storeId(order.getStoreId())
                .totalPrice(order.getTotalPrice())
                .status("CANCELLED")
                .items(responseItems)
                .build();
    }

    public List<OrderInfoDto> getUserOrders(Long userId) {
        List<Orders> orders = orderRepository.findByUserId(userId);

        List<OrderInfoDto> result = new ArrayList<>();

        for (Orders order : orders) {
            Store store = storeRepository.findById(Math.toIntExact(order.getStoreId()))
                    .orElseThrow(() -> new RuntimeException("가게 없음"));

            List<OrderItems> items = orderItemRepository.findByOrderOrderId(order.getOrderId());

            List<OrderItemInfoDto> itemDtos = items.stream().map(item -> {
                Product product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new RuntimeException("상품 없음"));
                return new OrderItemInfoDto(product.getName(), item.getQuantity(), item.getPrice());
            }).toList();

            result.add(new OrderInfoDto(order.getOrderId(), store.getName(), order.getStatus(), itemDtos));
        }

        return result;
    }
}
