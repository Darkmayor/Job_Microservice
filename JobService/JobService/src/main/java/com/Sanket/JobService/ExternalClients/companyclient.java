package com.Sanket.JobService.ExternalClients;

import com.Sanket.JobService.ExternalEntities.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CompanyService" ,
            url = "${company-service.uri}")
public interface companyclient {

    @GetMapping("/companies/getCompany/{id}")
    Company getcompany(@PathVariable Long id);
}
