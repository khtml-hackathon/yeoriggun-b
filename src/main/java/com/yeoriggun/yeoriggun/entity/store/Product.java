package com.yeoriggun.yeoriggun.entity.store;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private Long storeId;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;

    private Integer stock;

    private String imageUrl;
}