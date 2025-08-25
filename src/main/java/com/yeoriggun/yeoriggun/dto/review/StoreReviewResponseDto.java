package com.yeoriggun.yeoriggun.dto.review;


import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreReviewResponseDto {
    private Double averageRating;
    private List<ReviewDto> reviews;
}