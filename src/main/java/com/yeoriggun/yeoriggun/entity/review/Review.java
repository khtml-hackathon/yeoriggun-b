package com.yeoriggun.yeoriggun.entity.review;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Long userId;

    private Long storeId;

    private Integer rating;  // 별점

    private String comment;
}
