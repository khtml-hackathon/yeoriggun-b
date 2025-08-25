package com.yeoriggun.yeoriggun.entity.store;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false)
    private Long userId; // 사장님 user_id

    private String name;
    private String address;
    private String phone;
    private String openingHours;
    private String imageUrl;
    private String category;
    private String pickup;
}