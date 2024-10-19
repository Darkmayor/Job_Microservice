package com.Sanket.ReviewService.Controller;


import com.Sanket.ReviewService.DTO.ReviewMessageProducer;
import com.Sanket.ReviewService.Entities.Reviews;
import com.Sanket.ReviewService.Service.Review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMessageProducer reviewMessageProducer;

    @GetMapping
    public ResponseEntity<List<Reviews>> getCompanyReview(@RequestParam Long CompanyId){
        return new ResponseEntity<>(reviewService.getCompanyReview(CompanyId) , HttpStatus.OK);
    }

   @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long CompanyId , @RequestBody Reviews reviews){
        boolean isReviewSaved = reviewService.createReview(CompanyId, reviews);
        if(isReviewSaved){
            reviewMessageProducer.sendMessage(reviews);
            return new ResponseEntity<>("Review Added Successfully" , HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Review cannot be saved because company with id "+CompanyId + " does not exist" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{ReviewId}")
    public ResponseEntity<Reviews> getReviewByCompanyId(@PathVariable Long ReviewId){
        Reviews review = reviewService.getReviewWithId(ReviewId);
        if(review != null){
            return new ResponseEntity<>(review , HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{ReviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long ReviewId ,@RequestBody Reviews reviews){
        Reviews review = reviewService.updateReview(ReviewId, reviews);
        if(review!= null){
            return new ResponseEntity<>("Review Updated Successfully" , HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review could not be updated" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ReviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long ReviewId){
        boolean IsReviewDeleted = reviewService.deleteReviewById(ReviewId);
        if(IsReviewDeleted){
            return new ResponseEntity<>("Review Deleted Successfully" , HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Cannot be Deleted" , HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/averageRatings")
    public Double getAverageRatings(@RequestParam Long companyId){
        List<Reviews> reviewsList = reviewService.getCompanyReview(companyId);
        return reviewsList.stream()
                .mapToDouble(
                        Reviews::getRating
                )
                .average()
                .orElse(0.0);
    }

}
