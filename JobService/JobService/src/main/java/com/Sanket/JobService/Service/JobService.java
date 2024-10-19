package com.Sanket.JobService.Service;

import com.Sanket.JobService.Entities.Job;
import com.Sanket.JobService.Entities.JobWithCompanyDTO;

import java.util.List;
import java.util.Optional;

public interface JobService {

    List<JobWithCompanyDTO> findJobs();
    void CreateJob(Job job);
    JobWithCompanyDTO findJob(Long id);

    boolean deleteJob(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
