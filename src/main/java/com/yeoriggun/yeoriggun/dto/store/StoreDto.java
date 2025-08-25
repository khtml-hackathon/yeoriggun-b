package com.yeoriggun.yeoriggun.dto.store;

import com.yeoriggun.yeoriggun.entity.store.Store;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {
    private Long storeId;
    private String name;
    private String address;
    private String phone;
    private String openingHours;
    private String imageUrl;
    private String category;
    private String pickup;

    public static StoreDto fromEntity(Store store) {
        return StoreDto.builder()
                .storeId(store.getStoreId())
                .name(store.getName())
                .address(store.getAddress())
                .phone(store.getPhone())
                .openingHours(store.getOpeningHours())
                .imageUrl(store.getImageUrl())
                .category(store.getCategory())
                .pickup(store.getPickup())
                .build();
    }
}
