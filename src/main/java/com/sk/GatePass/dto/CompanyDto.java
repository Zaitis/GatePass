package com.sk.GatePass.dto;





import java.util.List;

public record CompanyDto(
        String companyName,
        String phone,
        String mail,
        List<EmployeeDto> employees
) {
}
