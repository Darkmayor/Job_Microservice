package com.Sanket.CompanyService.Controller;

import com.Sanket.CompanyService.Entities.Company;
import com.Sanket.CompanyService.Services.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/AllCompanies")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.findAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/getCompany/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.findCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company , HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateCompany/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany){
        if(companyService.updateCompany(id , updatedCompany)){
            return new ResponseEntity<>("Updated Successfully" , HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Cannot find any company with id "+ id,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createCompany")
    public ResponseEntity<String> CreateCompany(@RequestBody Company company){
        companyService.CreateCompany(company);
        return new ResponseEntity<>("Success" , HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        if(companyService.deleteCompanyById(id)){
            return new ResponseEntity<>("Company Deleted SuccessFully by id "+id,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("company with id "+id+ " Does not exist" , HttpStatus.NOT_FOUND);
        }
    }




}
