package com.sk.GatePass.controller;

import com.sk.GatePass.model.Company;
import com.sk.GatePass.service.CompanyService;
import jakarta.persistence.Transient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Transactional
    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompanies(){
        List<Company> list = companyService.getCompanies();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable(value="id")Long id){
        Company company = companyService.getCompany(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
    @PostMapping("/companies")
    public ResponseEntity<Company> addCompany(@RequestBody Company company){
        Company newCompany = companyService.addCompany(company);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }
    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable(value = "id")Long id, @RequestBody Company newCompany){
        Company company = companyService.updateCompany(id,newCompany);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(value = "id")Long id) {
        Company deleteCompany = companyService.getCompany(id);
        if(deleteCompany!=null) {
            companyService.deleteCompany(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
