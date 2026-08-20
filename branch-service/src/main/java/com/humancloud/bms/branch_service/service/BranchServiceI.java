package com.humancloud.bms.branch_service.service;

import com.humancloud.bms.branch_service.dto.BranchRequest;
import com.humancloud.bms.branch_service.dto.BranchResponse;
import com.humancloud.bms.branch_service.dto.BranchUpdateRequest;
import com.humancloud.bms.branch_service.exceptions.BranchCodeAlreadyExistsException;

import java.util.List;

public interface BranchServiceI {

    BranchResponse createBranch(BranchRequest req) throws BranchCodeAlreadyExistsException;

    BranchResponse getBranchById(Long id);

    List<BranchResponse> getAllBranches();

    BranchResponse updateBranch(Long id, BranchUpdateRequest req) throws BranchCodeAlreadyExistsException;

    void deleteBranch(Long id);
}
