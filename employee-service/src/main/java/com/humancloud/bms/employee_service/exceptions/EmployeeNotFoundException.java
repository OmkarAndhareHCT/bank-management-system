package com.humancloud.bms.employee_service.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("No employee found with id: " + id);
    }
}
