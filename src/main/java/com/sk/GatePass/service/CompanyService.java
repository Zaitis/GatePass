package com.sk.GatePass.service;

import com.sk.GatePass.model.Company;
import com.sk.GatePass.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    public Company addCompany(Company company){
        return companyRepository.save(company);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Long id){
        return companyRepository.findById(id).orElse(null);
    }

    public void deleteCompany(Long id){
        companyRepository.deleteById(id);
    }

    public Company updateCompany(Long id, Company newCompany){
        Company oldCompany=companyRepository.findById(id).get();
        oldCompany.setMail(newCompany.getMail());
        oldCompany.setName(newCompany.getName());
        oldCompany.setPhone(newCompany.getPhone());

        return companyRepository.save(oldCompany);

    }
}

