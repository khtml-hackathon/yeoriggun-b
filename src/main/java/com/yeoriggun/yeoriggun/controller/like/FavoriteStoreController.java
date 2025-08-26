package com.yeoriggun.yeoriggun.controller.like;

import com.yeoriggun.yeoriggun.service.like.FavoriteStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteStoreController {

    private final FavoriteStoreService favoriteStoreService;

    // 즐겨찾기 등록
    @PostMapping("/{userId}/{storeId}")
    public String addFavorite(@PathVariable Long userId, @PathVariable Long storeId) {
        favoriteStoreService.addFavorite(userId, storeId);
        return "즐겨찾기 등록 완료";
    }

    // 즐겨찾기 삭제
    @DeleteMapping("/{userId}/{storeId}")
    public String removeFavorite(@PathVariable Long userId, @PathVariable Long storeId) {
        favoriteStoreService.removeFavorite(userId, storeId);
        return "즐겨찾기 삭제 완료";
    }
}