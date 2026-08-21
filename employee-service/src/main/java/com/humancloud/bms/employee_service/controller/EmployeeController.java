package com.humancloud.bms.employee_service.controller;

import com.humancloud.bms.employee_service.dto.EmployeeRequest;
import com.humancloud.bms.employee_service.dto.EmployeeResponse;
import com.humancloud.bms.employee_service.dto.EmployeeUpdateRequest;
import com.humancloud.bms.employee_service.dto.ManagerAssignRequest;
import com.humancloud.bms.employee_service.enums.Role;
import com.humancloud.bms.employee_service.service.EmployeeServiceI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
/**
 * REST controller for managing bank employees.
 *
 * @author Omkar Andhare
 * @since 20-Aug-2026
 */
@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Validated
public class EmployeeController {

    private final EmployeeServiceI employeeServiceI;

    /**
     * Registers an employee. Admin only.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(@Valid @RequestBody EmployeeRequest req) {
        return employeeServiceI.createEmployee(req);
    }

    /**
     * One employee by id. Inactive employees are not returned.
     */
    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
        return employeeServiceI.getEmployeeById(id);
    }

    /**
     * Paged list, optionally filtered.
     * {@code GET /api/v1/employees?branchId=1&page=0&size=20&sort=name,asc}
     * The branchId filter is what branch-service calls for getBranchEmployees.
     */
    @GetMapping
    public Page<EmployeeResponse> getAllEmployees(
            @RequestParam(required = false) Long branchId,
            @RequestParam(required = false) Role position,
            @PageableDefault(size = 20, sort = "name") Pageable pageable) {
        return employeeServiceI.getAllEmployees(branchId, position, pageable);
    }

    /**
     * Updates details. Employee number is immutable and cannot be sent.
     */
    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id,
                                           @Valid @RequestBody EmployeeUpdateRequest req) {
        return employeeServiceI.updateEmployee(id, req);
    }

//    /** Assigns or clears the reporting manager. Rejects cycles with 409. */
//    @PatchMapping("/{id}/manager")
//    public EmployeeResponse setManager(@PathVariable Long id,
//                                       @RequestBody ManagerAssignRequest req) {
//        return employeeServiceI.setManager(id, req.managerId());
//    }

    /**
     * Soft delete - sets status to INACTIVE.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeServiceI.deleteEmployee(id);
    }
}
