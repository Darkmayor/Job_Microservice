package com.Sanket.JobService.Entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Job {

    // title , description , salary (min,max),Location
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_ids")
    private Long Id;

    @Column(nullable = false)
    private String Title;

    private String Description;

    @Column(nullable = false)
    private Integer MinSalary;

    @Column(nullable = false)
    private Integer MaxSalary;

    @Column(nullable = false)
    private String Location;

    private Integer Experience;

    private Long companyId;
}
