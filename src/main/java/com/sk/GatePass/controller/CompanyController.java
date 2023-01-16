package com.sk.GatePass.controller;

import com.sk.GatePass.model.Company;
import com.sk.GatePass.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompanies(){
        List<Company> list = companyService.getCompanies();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
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
        if(deleteCompany!=null)
        companyService.deleteCompany(id);
        else System.out.println("Brak firmy");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
