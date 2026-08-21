package com.humancloud.bms.branch_service.service;

import com.humancloud.bms.branch_service.dto.BranchRequest;
import com.humancloud.bms.branch_service.dto.BranchResponse;
import com.humancloud.bms.branch_service.dto.BranchUpdateRequest;
import com.humancloud.bms.branch_service.entity.Branch;
import com.humancloud.bms.branch_service.exceptions.BranchCodeAlreadyExistsException;
import com.humancloud.bms.branch_service.exceptions.BranchNotFoundException;
import com.humancloud.bms.branch_service.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchServiceI {

    private final BranchRepository repository;

    public BranchResponse createBranch(BranchRequest req) throws BranchCodeAlreadyExistsException {
        if (repository.existsByBranchCode(req.branchCode())) {
            throw new BranchCodeAlreadyExistsException(req.branchCode());
        }
        Branch branch = repository.save(Branch.builder().branchCode(req.branchCode()).address(req.address()).phone(req.phone()).city(req.city()).state(req.state()).status("ACTIVE").build());
        return BranchResponse.from(branch);
    }

    public BranchResponse getBranchById(Long id) {
        return repository.findById(id).map(BranchResponse::from).orElseThrow(() -> new BranchNotFoundException(id));
    }

    public List<BranchResponse> getAllBranches() {
        return repository.findAll().stream().map(BranchResponse::from).toList();
    }


    @Transactional
    public BranchResponse updateBranch(Long id, BranchUpdateRequest req) throws BranchCodeAlreadyExistsException {

        Branch branch = repository.findById(id).orElseThrow(() -> new BranchNotFoundException(id));

        branch.setAddress(req.address());
        branch.setPhone(req.phone());
        branch.setCity(req.city());
        branch.setState(req.state());

        repository.save(branch);

        return BranchResponse.from(branch);
    }

    @Override
    public void deleteBranch(Long id) {

        Branch branch = repository.findById(id).orElseThrow(() -> new BranchNotFoundException(id));;

        branch.setStatus("INACTIVE");

        repository.save(branch);

    }
}
