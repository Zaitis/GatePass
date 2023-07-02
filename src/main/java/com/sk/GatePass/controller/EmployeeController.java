package com.sk.GatePass.controller;


import com.sk.GatePass.dto.EmployeeDto;
import com.sk.GatePass.model.Employee;
import com.sk.GatePass.model.Role;
import com.sk.GatePass.service.CompanyService;
import com.sk.GatePass.service.EmployeeService;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;

    @Transactional
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }


    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDto newUser) {
        Employee employee = employeeService.addEmployee(createNewEmployee(newUser));
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto editUser) {
        return employeeService.updateEmployeeById(id, createNewEmployee(editUser))
                .map(user -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Employee createNewEmployee(EmployeeDto employeeDto) {
        return   Employee.builder()
                .firstName(employeeDto.firstName())
                .lastName(employeeDto.lastName())
                .mail(employeeDto.mail())
                .password(employeeDto.password())
                .phone(employeeDto.phone())
                .company(companyService.getCompany(employeeDto.company()))
                .role(employeeDto.role() != null ? employeeDto.role() : Role.USER)
                .build();

    }
}
