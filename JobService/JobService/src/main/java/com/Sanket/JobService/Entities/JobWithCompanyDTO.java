package com.Sanket.JobService.Entities;

import com.Sanket.JobService.ExternalEntities.Company;
import com.Sanket.JobService.ExternalEntities.Reviews;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
    private List<Reviews> reviews;
}
