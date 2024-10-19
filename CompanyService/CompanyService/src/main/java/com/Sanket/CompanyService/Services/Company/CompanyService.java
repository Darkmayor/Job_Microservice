package com.Sanket.CompanyService.Services.Company;

import com.Sanket.CompanyService.Entities.Company;
import com.Sanket.CompanyService.Messages.ReviewMessage;


import java.util.List;

public interface CompanyService {
    List<Company> findAllCompanies();
    Company findCompanyById(Long id);
    boolean updateCompany(Long id,Company company);
    void CreateCompany(Company company);
    boolean deleteCompanyById(Long id);
    void updateCompanyRating(ReviewMessage reviewMessage);
}
