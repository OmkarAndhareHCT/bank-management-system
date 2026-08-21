package com.humancloud.bms.employee_service.service;

import com.humancloud.bms.employee_service.client.BranchClient;
import com.humancloud.bms.employee_service.dto.EmployeeRequest;
import com.humancloud.bms.employee_service.dto.EmployeeResponse;
import com.humancloud.bms.employee_service.dto.EmployeeUpdateRequest;
import com.humancloud.bms.employee_service.entity.Employee;
import com.humancloud.bms.employee_service.enums.Role;
import com.humancloud.bms.employee_service.enums.Status;
import com.humancloud.bms.employee_service.exceptions.DuplicateEmailException;
import com.humancloud.bms.employee_service.exceptions.EmployeeNotFoundException;
import com.humancloud.bms.employee_service.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeServiceI {

    private final EmployeeRepository repository;
    private final BranchClient branchClient;

    @Override
    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest req) {

        if (repository.existsByEmail(req.email())) {
            throw new DuplicateEmailException(req.email());
        }

        branchClient.assertBranchExists(req.branchId());

        String employeeNumber = "EMP-" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        Employee employee = repository.save(Employee.builder()
                .employeeNumber(employeeNumber)
                .name(req.name())
                .phone(req.phone())
                .email(req.email())
                .position(req.role())
                .managerId(req.managerId())
                .branchId(req.branchId())
                .joiningDate(req.joiningDate())
                .status(Status.ACTIVE)
                .build());

        return EmployeeResponse.from(employee);
    }


    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        return EmployeeResponse.from(requireActive(id));
    }

    @Override
    public Page<EmployeeResponse> getAllEmployees(Long branchId, Role role, Pageable pageable) {
        Page<Employee> page;
        if (branchId != null) {
            page = repository.findByBranchIdAndStatus(branchId, Status.ACTIVE, pageable);
        } else if (role != null) {
            page = repository.findByPositionAndStatus(role, Status.ACTIVE, pageable);
        } else {
            page = repository.findByStatus(Status.ACTIVE, pageable);
        }
        return page.map(EmployeeResponse::from);
    }


    /**
     * Single place that loads an employee. Nothing else calls findById directly.
     */
    private Employee requireActive(Long id) {
        return repository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest req) {

        Employee employee = requireActive(id);

        // Someone *else* holding this email is a conflict; the employee's own is fine.
        if (repository.existsByEmailAndIdNot(req.email(), id)) {
            throw new DuplicateEmailException(req.email());
        }

        if (!employee.getBranchId().equals(req.branchId())) {
            branchClient.assertBranchExists(req.branchId());   // only call out if it changed
        }

        employee.setName(req.name());
        employee.setPhone(req.phone());
        employee.setEmail(req.email());
        employee.setPosition(req.role());
        employee.setBranchId(req.branchId());

        return EmployeeResponse.from(repository.save(employee));
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {

        Employee employee = requireActive(id);   // already-inactive gives 404, not a silent re-save

        // Do not orphan direct reports.
        if (repository.existsByManagerIdAndStatus(id, Status.ACTIVE)) {
            throw new IllegalStateException(
                    "Employee " + id + " still manages active employees. Reassign them first.");
        }

        employee.setStatus(Status.INACTIVE);
        repository.save(employee);
    }


}


