package com.humancloud.bms.branch_service.controller;


import com.humancloud.bms.branch_service.dto.BranchRequest;
import com.humancloud.bms.branch_service.dto.BranchResponse;
import com.humancloud.bms.branch_service.dto.BranchUpdateRequest;
import com.humancloud.bms.branch_service.exceptions.BranchCodeAlreadyExistsException;
import com.humancloud.bms.branch_service.service.BranchServiceI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing bank branch details.
 *
 * <p>Provides APIs for creating, retrieving, and updating bank branches.</p>
 *
 * @author Omkar Andhare
 * @since 19-Aug-2026
 */
@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchServiceI service;

    /**
     * Creates a new bank branch.
     *
     * <p>This API is intended for admin users. The request is validated
     * before the branch is created.</p>
     *
     * @param req branch details received from the client
     * @return newly created branch details
     * @throws com.humancloud.bms.branch_service.exceptions.BranchCodeAlreadyExistsException if the branch code already exists
     * @apiNote HTTP Method: POST
     * @apiNote Endpoint: /api/v1/branches/users
     * @apiNote Response Status: 201 CREATED
     * @since 19-Aug-2026
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BranchResponse createBranch(@Valid @RequestBody BranchRequest req) throws BranchCodeAlreadyExistsException {
        return service.createBranch(req);
    }

    /**
     * Retrieves a bank branch by its unique ID.
     *
     * @param id unique identifier of the branch
     * @return branch details
     * @apiNote HTTP Method: GET
     * @apiNote Endpoint: /api/v1/branches/{id}
     * @apiNote Response Status: 200 OK
     * @since 19-Aug-2026
     */
    @GetMapping("/{id}")
    public BranchResponse getBranchById(@PathVariable Long id) {
        return service.getBranchById(id);
    }

    /**
     * Retrieves all bank branches.
     *
     * @return list of all branch details
     * @apiNote HTTP Method: GET
     * @apiNote Endpoint: /api/v1/branches
     * @apiNote Response Status: 200 OK
     * @since 19-Aug-2026
     */
    @GetMapping("/getAllBranches")
    public List<BranchResponse> getAllBranches() {
        return service.getAllBranches();
    }

    /**
     * Updates an existing bank branch.
     *
     * <p>This API is intended for admin users.</p>
     *
     * @param id  unique identifier of the branch to update
     * @param req updated branch details
     * @return updated branch details
     * @apiNote HTTP Method: PUT
     * @apiNote Endpoint: /api/v1/branches/{id}
     * @apiNote Response Status: 200 OK
     * @since 19-Aug-2026
     */
    @PutMapping("/update/{id}")
    public BranchResponse updateBranch(@PathVariable Long id,
                                       @Valid @RequestBody BranchUpdateRequest req) throws BranchCodeAlreadyExistsException {
        return service.updateBranch(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBranch(@PathVariable Long id) {
         service.deleteBranch(id);
    }




    //{TODO}:[19-08-2026] need one functionality for fetching the employees of specific branches
}