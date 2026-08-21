package com.humancloud.bms.employee_service.exceptions;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("Employee already exists with email: " + email);
    }
}
