package com.yeoriggun.yeoriggun.controller.like;

import com.yeoriggun.yeoriggun.service.like.FavoriteStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
@Tag(name = "즐겨찾기", description = "즐겨찾기 관련 API")
public class FavoriteStoreController {

    private final FavoriteStoreService favoriteStoreService;

    @Operation(summary = "즐겨찾기 등록", description = "가게를 즐겨찾기에 등록합니다.")
    @PostMapping("/{userId}/{storeId}")
    public String addFavorite(@PathVariable Long userId, @PathVariable Long storeId) {
        favoriteStoreService.addFavorite(userId, storeId);
        return "즐겨찾기 등록 완료";
    }

    @Operation(summary = "즐겨찾기 삭제", description = "가게를 즐겨찾기에서 제외합니다.")
    @DeleteMapping("/{userId}/{storeId}")
    public String removeFavorite(@PathVariable Long userId, @PathVariable Long storeId) {
        favoriteStoreService.removeFavorite(userId, storeId);
        return "즐겨찾기 삭제 완료";
    }
}