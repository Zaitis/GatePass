package com.sk.GatePass.service;

import com.sk.GatePass.model.Company;
import com.sk.GatePass.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {


    private final CompanyRepository companyRepository;

    public Company addCompany(Company company){
        return companyRepository.save(company);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Long id){
        System.out.println(companyRepository.findById(id).orElseThrow());

        return companyRepository.findById(id).orElseThrow();
    }

    public void deleteCompany(Long id){
        companyRepository.deleteById(id);
    }

    public Company updateCompany(Long id, Company newCompany){
        Company oldCompany=companyRepository.findById(id).get();
        oldCompany.setMail(newCompany.getMail());
        oldCompany.setCompanyName(newCompany.getCompanyName());
        oldCompany.setPhone(newCompany.getPhone());

        return companyRepository.save(oldCompany);

    }
}

