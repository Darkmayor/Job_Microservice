package com.Sanket.JobService.Controller;


import com.Sanket.JobService.Entities.Job;
import com.Sanket.JobService.Entities.JobWithCompanyDTO;
import com.Sanket.JobService.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/AllJobs")
    public ResponseEntity<List<JobWithCompanyDTO>> getAllJobs(){
        return new ResponseEntity<>(jobService.findJobs(), HttpStatus.OK);
    }

    @PostMapping("/CreateJob")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.CreateJob(job);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobWithCompanyDTO> getJob(@PathVariable Long id){
       JobWithCompanyDTO jobWithCompanyDTO =  jobService.findJob(id);
        if(jobWithCompanyDTO != null){
            return new ResponseEntity<>(jobWithCompanyDTO,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJob(id);
        if(deleted){
            return new ResponseEntity<>("Deleted SuccessFully" , HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Job Found",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateJob/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updatedJob){
        if(jobService.updateJob(id,updatedJob)){
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Job exist with id" + id , HttpStatus.NOT_FOUND);
        }
    }
}
