package com.Sanket.JobService.ExternalEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {

    private Long id;

    private String description;

    private String title;

    private double rating;

}
