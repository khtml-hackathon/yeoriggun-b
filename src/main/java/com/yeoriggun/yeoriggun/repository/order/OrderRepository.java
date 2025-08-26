package com.yeoriggun.yeoriggun.repository.order;

import com.yeoriggun.yeoriggun.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);
}
