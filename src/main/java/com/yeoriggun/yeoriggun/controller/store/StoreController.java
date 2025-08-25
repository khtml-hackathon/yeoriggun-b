package com.yeoriggun.yeoriggun.controller.store;

import com.yeoriggun.yeoriggun.dto.store.StoreDto;
import com.yeoriggun.yeoriggun.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public List<StoreDto> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/category/{category}")
    public List<StoreDto> getAllCategories(@PathVariable String category) {
        return storeService.getStoresByCategory(category);
    }
}
