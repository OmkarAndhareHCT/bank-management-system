package com.humancloud.bms.employee_service.dto;

import com.humancloud.bms.employee_service.entity.Employee;
import com.humancloud.bms.employee_service.enums.Role;
import com.humancloud.bms.employee_service.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmployeeResponse(
        Long id, String employeeNumber, String name, String phone, String email,
        Role role, Long managerId, Long branchId,
        LocalDate joiningDate, Status status,
        LocalDateTime createdAt, LocalDateTime updatedAt
) {
    public static EmployeeResponse from(Employee e) {
        return new EmployeeResponse(
                e.getId(), e.getEmployeeNumber(), e.getName(), e.getPhone(), e.getEmail(),
                e.getPosition(), e.getManagerId(), e.getBranchId(),
                e.getJoiningDate(), e.getStatus(), e.getCreatedAt(), e.getUpdatedAt());
    }
}
