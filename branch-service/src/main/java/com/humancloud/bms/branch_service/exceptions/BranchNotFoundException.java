package com.humancloud.bms.branch_service.exceptions;

public class BranchNotFoundException extends RuntimeException {

    public BranchNotFoundException(Long id) {
        super("No branch found with id: " + id);
    }
}
