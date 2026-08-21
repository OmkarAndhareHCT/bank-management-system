package com.humancloud.bms.employee_service.exceptions;

public class BranchServiceUnavailableException extends RuntimeException {
    public BranchServiceUnavailableException(Long branchId, Throwable cause) {
        super("Could not verify branch " + branchId + ": branch-service unavailable", cause);
    }
}
