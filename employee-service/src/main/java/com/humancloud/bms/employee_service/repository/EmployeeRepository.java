package com.humancloud.bms.employee_service.repository;

import com.humancloud.bms.employee_service.entity.Employee;
import com.humancloud.bms.employee_service.enums.Role;
import com.humancloud.bms.employee_service.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // --- uniqueness checks
    boolean existsByEmployeeNumber(String employeeNumber);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    // --- reads that respect soft delete
    Optional<Employee> findByIdAndStatus(Long id, Status status);

    Page<Employee> findByStatus(Status status, Pageable pageable);

    Page<Employee> findByBranchIdAndStatus(Long branchId, Status status, Pageable pageable);

    List<Employee> findByBranchIdAndStatus(Long branchId, Status status);

    Page<Employee> findByPositionAndStatus(Role role, Status status, Pageable pageable);

    // --- manager chain
    List<Employee> findByManagerIdAndStatus(Long managerId, Status status);

    boolean existsByManagerIdAndStatus(Long managerId, Status status);

    // --- employee number generation
    @Query("select max(e.employeeNumber) from Employee e")
    Optional<String> findMaxEmployeeNumber();
}
