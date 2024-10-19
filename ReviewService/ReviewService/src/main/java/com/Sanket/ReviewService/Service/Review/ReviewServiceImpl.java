package com.Sanket.ReviewService.Service.Review;


import com.Sanket.ReviewService.Entities.Reviews;
import com.Sanket.ReviewService.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
//    @Autowired
//    private CompanyService companyService;

    @Override
    public List<Reviews> getCompanyReview(Long CompanyId) {
        return reviewRepository.findByCompanyId(CompanyId);
    }

    @Override
    public Reviews getReviewWithId(Long ReviewId) {
        return reviewRepository.findById(ReviewId).orElse(null);
    }


    @Override
    public boolean createReview(Long CompanyId,Reviews review) {
        // find company and then set review
//        Company company = companyService.findCompanyById(CompanyId);
        if(CompanyId != null && review != null){
            review.setCompanyId(CompanyId);
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Reviews updateReview(Long ReviewId, Reviews UpdatedReview){
        Reviews review = reviewRepository.findById(ReviewId).orElse(null);
        if(review != null){
            review.setDescription(UpdatedReview.getDescription());
            review.setTitle(UpdatedReview.getTitle());
            review.setRating(UpdatedReview.getRating());
            review.setCompanyId(UpdatedReview.getCompanyId());
            reviewRepository.save(review);
            return review;
        }
        else{
            return null;
        }
    }

    @Override
    public boolean deleteReviewById(Long ReviewId) {
        Reviews review = reviewRepository.findById(ReviewId).orElse(null);
        if(review != null){
            reviewRepository.delete(review);
            return true;
        }else{
            return false;
        }

    }
}
