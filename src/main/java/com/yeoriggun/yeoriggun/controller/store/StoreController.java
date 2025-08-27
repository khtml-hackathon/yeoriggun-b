package com.yeoriggun.yeoriggun.controller.store;

import com.yeoriggun.yeoriggun.dto.store.StoreDto;
import com.yeoriggun.yeoriggun.service.store.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
@Tag(name = "가게 정보", description = "가게 정보 관련 API")
public class StoreController {
    private final StoreService storeService;

    @Operation(summary = "가게 리스트", description = "가게 리스트를 가져옵니다.")
    @GetMapping
    public List<StoreDto> getAllStores() {
        return storeService.getAllStores();
    }

    @Operation(summary = "카테고리 별 가게 리스트", description = "카테고리 별 가게 리스트를 가져옵니다.")
    @GetMapping("/category/{category}")
    public List<StoreDto> getAllCategories(@PathVariable String category) {
        return storeService.getStoresByCategory(category);
    }
}
