package com.yeoriggun.yeoriggun.repository.store;

import com.yeoriggun.yeoriggun.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    List<Store> findByCategory(String category);
}
