package com.Sanket.ReviewService.Service.Review;



import com.Sanket.ReviewService.Entities.Reviews;

import java.util.List;

// 5:17
public interface ReviewService {
    List<Reviews> getCompanyReview(Long CompanyId);
    Reviews getReviewWithId(Long ReviewId);
    boolean createReview(Long CompanyId, Reviews review);
    Reviews updateReview(Long ReviewId, Reviews review);
    boolean deleteReviewById(Long ReviewId);
}
