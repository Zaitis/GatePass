//package com.sk.GatePass.controller;
//
//import com.sk.GatePass.model.Company;
//import com.sk.GatePass.service.CompanyService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class CompanyControllerTest {
//
//    @InjectMocks
//    CompanyController companyController;
//
//    @Mock
//    private CompanyService companyService;
//
//    private Company company1;
//    private Company company2;
//    private List<Company> companyList;
//
//    @BeforeEach
//    void setUp() {
//        company1 = new Company("ABC Company", "666555444", "abc@company.com");
//        company1.setId(1L);
//        company2 = new Company("XYZ Company", "444555666", "xyz@company.com");
//        company2.setId(2L);
//
//        companyList = new ArrayList<>();
//        companyList.add(company1);
//        companyList.add(company2);
//    }
//
//    @Test
//    void shouldGetCompanies() {
//        when(companyService.getCompanies()).thenReturn(companyList);
//
//        ResponseEntity<List<Company>> responseEntity = companyController.getCompanies();
//
//        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        Assertions.assertEquals(companyList, responseEntity.getBody());
//    }
//
//    @Test
//    void shouldGetCompany() {
//        Long companyId = 1L;
//        when(companyService.getCompany(companyId)).thenReturn(company1);
//
//        ResponseEntity<Company> responseEntity = companyController.getCompany(companyId);
//
//        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        Assertions.assertEquals(company1, responseEntity.getBody());
//    }
//
//    @Test
//    void shouldAddCompany() {
//        when(companyService.addCompany(company1)).thenReturn(company1);
//
//        ResponseEntity<Company> responseEntity = companyController.addCompany(company1);
//
//        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        Assertions.assertEquals(company1, responseEntity.getBody());
//    }
//
//    @Test
//    void shouldUpdateCompany() {
//        Long companyId = 1L;
//        Company updatedCompany = new Company("CBA Company", "333555444", "company@company.com");
//        updatedCompany.setId(companyId);
//
//        when(companyService.updateCompany(companyId, updatedCompany)).thenReturn(updatedCompany);
//
//        ResponseEntity<Company> responseEntity = companyController.updateCompany(companyId, updatedCompany);
//
//        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        Assertions.assertEquals(updatedCompany, responseEntity.getBody());
//    }
//
//    @Test
//    void shouldDeleteCompany() {
//        Long companyId = 1L;
//        when(companyService.getCompany(companyId)).thenReturn(company1);
//
//        ResponseEntity<?> responseEntity = companyController.deleteCompany(companyId);
//
//        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        verify(companyService, Mockito.times(1)).deleteCompany(companyId);
//    }
//
//    @Test
//    void shouldNotFoundWhenDeleteCompany() {
//        Long companyId = 2L;
//        when(companyService.getCompany(companyId)).thenReturn(null);
//        ResponseEntity<?> responseEntity = companyController.deleteCompany(companyId);
//        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    }
//}
