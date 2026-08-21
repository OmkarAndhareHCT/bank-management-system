package com.humancloud.bms.employee_service.exceptions;

public class BranchNotFoundException extends RuntimeException {
    public BranchNotFoundException(Long branchId) {
        super("No branch found with id: " + branchId);
    }
}
