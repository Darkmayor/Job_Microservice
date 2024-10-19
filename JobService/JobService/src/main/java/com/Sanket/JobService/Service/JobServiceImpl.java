package com.Sanket.JobService.Service;


import com.Sanket.JobService.Entities.Job;
import com.Sanket.JobService.Entities.JobWithCompanyDTO;
import com.Sanket.JobService.ExternalEntities.Company;
import com.Sanket.JobService.ExternalEntities.Reviews;
import com.Sanket.JobService.Repository.JobRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;
import com.Sanket.JobService.ExternalClients.companyclient;
import com.Sanket.JobService.ExternalClients.reviewClient;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private companyclient companyclient;
    @Autowired
    private reviewClient reviewClient;
//    @Autowired
//    private RestTemplate restTemplate;

    @Override
//    @CircuitBreaker(name = "companyBreaker" , fallbackMethod = "companyBreakerFallback")
//        @Retry(name = "companyBreaker" , fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker" , fallbackMethod = "companyBreakerFallback")
    public List<JobWithCompanyDTO> findJobs() {

       List<Job> jobs = jobRepository.findAll();

       return jobs.stream()
               .map(this::convertToDto)
               .collect(Collectors.toList());
    }

    private String companyBreakerFallback(Exception e){
        return "Some Services are down please wait for some time or try again later";
    }


    private JobWithCompanyDTO convertToDto(Job job){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
//        Company company =  restTemplate.getForObject(
//                "http://CompanyService:8082/Companies/getCompany/"+ job.getId(),
//                Company.class
//        );
        Company company = companyclient.getcompany(job.getCompanyId());
//        ResponseEntity<List<Reviews>> ReviewResponse = restTemplate.exchange(
//                "http://ReviewService:8083/reviews?CompanyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Reviews>>() {
//                }
//        );
        List<Reviews> reviews = reviewClient.getReviews(job.getCompanyId());

        // convert the response into list
//        List<Reviews> reviews = ReviewResponse.getBody();
        if(Objects.equals(job.getCompanyId(), company.getId())){
            jobWithCompanyDTO.setCompany(company);
            jobWithCompanyDTO.setReviews(reviews);
//            jobWithCompanyDTOList.add(jobWithCompanyDTO);
            return jobWithCompanyDTO;
        }else{
            return null;
        }
    }

    @Override
    public void CreateJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobWithCompanyDTO findJob(Long id) {
        Job job =  jobRepository.findById(id).orElse(null);
        if(job == null){
            return null;
        }
        return convertToDto(job);
    }

    @Override
    public boolean deleteJob(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        // find job
       Job job = jobRepository.findById(id).orElse(null);
       if(job != null){
           job.setDescription(updatedJob.getDescription());
           job.setTitle(updatedJob.getTitle());
           job.setLocation(updatedJob.getLocation());
           job.setMaxSalary(updatedJob.getMaxSalary());
           job.setMinSalary(updatedJob.getMinSalary());
           job.setExperience(updatedJob.getExperience());
           return true;
       }else{
           return false;
       }
    }

}
