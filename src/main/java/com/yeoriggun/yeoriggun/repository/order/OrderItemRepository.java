package com.yeoriggun.yeoriggun.repository.order;

import com.yeoriggun.yeoriggun.entity.order.OrderItems;
import com.yeoriggun.yeoriggun.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
    List<OrderItems> findByOrderOrderId(Long orderId);
}