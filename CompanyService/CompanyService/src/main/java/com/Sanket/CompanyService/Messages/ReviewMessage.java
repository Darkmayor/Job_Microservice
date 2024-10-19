package com.Sanket.CompanyService.Messages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewMessage {

    private Long Id;

    private String Description;

    private String title;

    private Long CompanyId;

    private double rating;
}
