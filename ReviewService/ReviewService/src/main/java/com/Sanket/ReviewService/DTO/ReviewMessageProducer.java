package com.Sanket.ReviewService.DTO;

import com.Sanket.ReviewService.Entities.Reviews;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Reviews review){
        ReviewMessage reviewMessage = new ReviewMessage();

        reviewMessage.setId(review.getId());
        reviewMessage.setTitle(review.getTitle());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setCompanyId(review.getCompanyId());
        reviewMessage.setRating(review.getRating());

        rabbitTemplate.convertAndSend("companyRatingQueue" , reviewMessage);
    }
}
