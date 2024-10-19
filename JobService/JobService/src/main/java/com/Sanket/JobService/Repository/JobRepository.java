package com.Sanket.JobService.Repository;

import com.Sanket.JobService.Entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>{

}
