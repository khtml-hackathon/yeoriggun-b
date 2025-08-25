package com.yeoriggun.yeoriggun.service.store;

import com.yeoriggun.yeoriggun.dto.store.StoreDto;
import com.yeoriggun.yeoriggun.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    //전체 조회
    public List<StoreDto> getAllStores() {
        return storeRepository.findAll()
                .stream()
                .map(StoreDto::fromEntity)
                .collect(Collectors.toList());
    }

    //카테고리 별 조회
    public List<StoreDto> getStoresByCategory(String category) {
        return storeRepository.findByCategory(category)
                .stream()
                .map(StoreDto::fromEntity)
                .collect(Collectors.toList());
    }
}
