package com.sk.GatePass.repository;

import com.sk.GatePass.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByMail(String mail);

    @Query("select c from Employee c " +
            "where lower(c.firstName) like lower(concat('%', :filterText, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :filterText, '%'))")
    List<Employee> search(String filterText);
}
