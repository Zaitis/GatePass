package com.sk.GatePass.view.company;
import com.sk.GatePass.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CompanyFormTest {

        private Company company1, company2;


        @BeforeEach
        public void setupData() {

            company1 = Company.builder()
                    .id(1L)
                    .companyName("SK")
                    .phone("12345678")
                    .mail("admin@sk.com")
                    .employees(null)
                    .build();

            company2 = Company.builder()
                    .id(2L)
                    .companyName("Gate Way")
                    .phone("87654321")
                    .mail("admin@gateway.com")
                    .employees(null)
                    .build();
        }

    @Test
    public void formFieldsPopulated() {
         //given
        CompanyForm form1 = new CompanyForm();
        CompanyForm form2 = new CompanyForm();

        //when
        form1.setCompany(company1);
        form2.setCompany(company2);

        //then
        assertEquals("SK", form1.companyName.getValue());
        assertEquals("12345678", form1.phone.getValue());
        assertEquals("admin@sk.com", form1.mail.getValue());

        assertEquals("Gate Way", form2.companyName.getValue());
        assertEquals("87654321", form2.phone.getValue());
        assertEquals("admin@gateway.com", form2.mail.getValue());
    }

    @Test
    public void saveEventHasCorrectValues() {
            //given
            CompanyForm form = new CompanyForm();
            Company company = new Company();
            form.setCompany(company);
            form.companyName.setValue("Lidl");
            form.phone.setValue("666666666");
            form.mail.setValue("john@doe.com");
            AtomicReference<Company> savedCompanyRef = new AtomicReference<>(null);

        //when
        form.addSaveListener(e -> {
            savedCompanyRef.set(e.getCompany());
        });
        form.save.click();
        Company savedCompany = savedCompanyRef.get();

        //then
        assertEquals("Lidl", savedCompany.getCompanyName());
        assertEquals("666666666", savedCompany.getPhone());
        assertEquals("john@doe.com", savedCompany.getMail());

    }



    }

