package com.yeoriggun.yeoriggun.repository.like;

import com.yeoriggun.yeoriggun.entity.like.FavoriteStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteStoreRepository extends JpaRepository<FavoriteStore, Long> {
    Optional<FavoriteStore> findByUserIdAndStoreId(Long userId, Long storeId);
    void deleteByUserIdAndStoreId(Long userId, Long storeId);
}
