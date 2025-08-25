package com.yeoriggun.yeoriggun.dto.review;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private Long reviewId;
    private Long userId;
    private Long storeId;
    private Integer rating;
    private String comment;
}