package com.Sanket.CompanyService.Services.Company;

import com.Sanket.CompanyService.Clients.ReviewClient;
import com.Sanket.CompanyService.Entities.Company;
import com.Sanket.CompanyService.Messages.ReviewMessage;
import com.Sanket.CompanyService.Repositories.CompanyRepository;

import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ReviewClient reviewClient;

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }
    @Override
    public Company findCompanyById(Long id){
        return companyRepository.findById(id).orElse(null);
    }
    @Override
    public boolean updateCompany(Long id,Company company) {
        Company company1 = findCompanyById(id);
        if(company1 != null){
            company.setCompany_Name(company1.getCompany_Name());
            company.setDescription(company.getDescription());
//            company.setJobsList(company1.getJobsList());
        }
        return false;
    }

    @Override
    public void CreateCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {

        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
//        System.out.print(reviewMessage.getDescription());
        //find company using reviewmessage company id
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow(
                ()-> new NotFoundException("cannot find any company with id "+reviewMessage.getCompanyId())
        );
        //get average rating
        Double AverageRatings = reviewClient.getAverageRatings(reviewMessage.getCompanyId());
        company.setAverageCompanyRating(AverageRatings);
        companyRepository.save(company);
    }

}
