package com.yeoriggun.yeoriggun.repository.store;

import com.yeoriggun.yeoriggun.entity.store.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStoreId(Long storeId);
}