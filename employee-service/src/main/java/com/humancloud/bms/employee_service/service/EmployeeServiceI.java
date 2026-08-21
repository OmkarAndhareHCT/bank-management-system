package com.humancloud.bms.employee_service.service;

import com.humancloud.bms.employee_service.dto.EmployeeRequest;
import com.humancloud.bms.employee_service.dto.EmployeeResponse;
import com.humancloud.bms.employee_service.dto.EmployeeUpdateRequest;
import com.humancloud.bms.employee_service.enums.Role;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface EmployeeServiceI {

    EmployeeResponse createEmployee(EmployeeRequest req);

    EmployeeResponse getEmployeeById(Long id);

    Page<EmployeeResponse> getAllEmployees(Long branchId, Role role, Pageable pageable);

    EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest req);

//    EmployeeResponse setManager(Long id, Long managerId);

    void deleteEmployee(Long id);
}
