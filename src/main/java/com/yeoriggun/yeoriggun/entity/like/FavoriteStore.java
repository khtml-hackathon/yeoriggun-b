package com.yeoriggun.yeoriggun.entity.like;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorite_stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long storeId;
}