package com.Sanket.JobService.ExternalClients;

import com.Sanket.JobService.ExternalEntities.Reviews;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ReviewService" , url = "${review-service.uri}")
public interface reviewClient {

    @GetMapping("/reviews")
    List<Reviews> getReviews(@RequestParam("CompanyId") Long CompanyId);
}
