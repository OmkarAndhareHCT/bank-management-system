package com.humancloud.bms.branch_service.exceptions;

public class BranchCodeAlreadyExistsException extends RuntimeException {

    public BranchCodeAlreadyExistsException(String branchCode){
        super("Branch code already exists: " + branchCode);
    }
}
