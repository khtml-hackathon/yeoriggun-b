package com.yeoriggun.yeoriggun.service.like;

import com.yeoriggun.yeoriggun.entity.like.FavoriteStore;
import com.yeoriggun.yeoriggun.repository.like.FavoriteStoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteStoreService {

    private final FavoriteStoreRepository favoriteStoreRepository;

    // 즐겨찾기 등록
    public void addFavorite(Long userId, Long storeId) {
        favoriteStoreRepository.findByUserIdAndStoreId(userId, storeId)
                .ifPresent(fav -> {
                    throw new RuntimeException("이미 즐겨찾기에 등록된 가게입니다.");
                });

        FavoriteStore favoriteStore = FavoriteStore.builder()
                .userId(userId)
                .storeId(storeId)
                .build();

        favoriteStoreRepository.save(favoriteStore);
    }

    // 즐겨찾기 삭제
    @Transactional
    public void removeFavorite(Long userId, Long storeId) {
        favoriteStoreRepository.findByUserIdAndStoreId(userId, storeId)
                .orElseThrow(() -> new RuntimeException("즐겨찾기에 등록된 가게가 아닙니다."));

        favoriteStoreRepository.deleteByUserIdAndStoreId(userId, storeId);
    }
}