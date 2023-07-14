package com.sk.GatePass.repository;

import com.sk.GatePass.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c from Company c " +
            "where lower(c.companyName) like lower(concat('%', :filterText, '%')) " +
            "or lower(c.mail) like lower(concat('%', :filterText, '%'))")
    List<Company> search(String filterText);
}
