package com.yeoriggun.yeoriggun.controller.review;

import com.yeoriggun.yeoriggun.dto.review.ReviewDto;
import com.yeoriggun.yeoriggun.dto.review.StoreReviewResponseDto;
import com.yeoriggun.yeoriggun.service.review.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Tag(name = "리뷰", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 등록", description = "특정 가게의 리뷰를 등록합니다.")
    @PostMapping
    public ReviewDto addReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.addReview(reviewDto);
    }

    // 특정 가게 리뷰 + 평균 별점 조회
    @Operation(summary = "특정 가게 리뷰 + 평균 별점 조회", description = "특정 가게의 리뷰와 평균 별점을 조회합니다")
    @GetMapping("/store/{storeId}")
    public StoreReviewResponseDto getStoreReviews(@PathVariable Long storeId) {
        return reviewService.getReviewsByStoreId(storeId);
    }
}