package com.yeoriggun.yeoriggun.controller.review;

import com.yeoriggun.yeoriggun.dto.review.ReviewDto;
import com.yeoriggun.yeoriggun.dto.review.StoreReviewResponseDto;
import com.yeoriggun.yeoriggun.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 등록
    @PostMapping
    public ReviewDto addReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.addReview(reviewDto);
    }

    // 특정 가게 리뷰 + 평균 별점 조회
    @GetMapping("/store/{storeId}")
    public StoreReviewResponseDto getStoreReviews(@PathVariable Long storeId) {
        return reviewService.getReviewsByStoreId(storeId);
    }
}