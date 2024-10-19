package com.Sanket.JobService.ExternalEntities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private Long Id;

    private String company_Name;

    private String Description;


}