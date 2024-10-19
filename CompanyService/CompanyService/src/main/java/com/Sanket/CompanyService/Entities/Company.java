package com.Sanket.CompanyService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long Id;
    @Column(nullable = false)
    private String company_Name;

    @Column(nullable = false)
    private String Description;

    private double averageCompanyRating;

}