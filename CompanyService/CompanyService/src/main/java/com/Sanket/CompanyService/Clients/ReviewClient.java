package com.Sanket.CompanyService.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ReviewService",
            url = "${review-service.uri}")
public interface ReviewClient {

    @GetMapping("/reviews/averageRatings")
    Double getAverageRatings(@RequestParam("companyId") Long companyId);
}
