package com.yeoriggun.yeoriggun.service.review;


import com.yeoriggun.yeoriggun.dto.review.ReviewDto;
import com.yeoriggun.yeoriggun.dto.review.StoreReviewResponseDto;
import com.yeoriggun.yeoriggun.entity.review.Review;
import com.yeoriggun.yeoriggun.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    //리뷰 등록
    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = Review.builder()
                .userId(reviewDto.getUserId())
                .storeId(reviewDto.getStoreId())
                .rating(reviewDto.getRating())
                .comment(reviewDto.getComment())
                .build();

        Review saved = reviewRepository.save(review);
        reviewDto.setReviewId(saved.getReviewId());
        return reviewDto;
    }

    //특정 가게 리뷰 + 평균 별점 조회
    public StoreReviewResponseDto getReviewsByStoreId(Long storeId) {
        List<Review> reviews = reviewRepository.findByStoreId(storeId);

        double averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        List<ReviewDto> reviewDtos = reviews.stream()
                .map(r -> ReviewDto.builder()
                        .reviewId(r.getReviewId())
                        .userId(r.getUserId())
                        .storeId(r.getStoreId())
                        .rating(r.getRating())
                        .comment(r.getComment())
                        .build())
                .collect(Collectors.toList());

        return StoreReviewResponseDto.builder()
                .averageRating(averageRating)
                .reviews(reviewDtos)
                .build();
    }
}