package com.humancloud.bms.branch_service.repository;

import com.humancloud.bms.branch_service.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Optional<Branch> findByBranchCode(String branchCode);

    List<Branch> findByCityIgnoreCase(String city);

    boolean existsByBranchCode(String branchCode);

    boolean existsByBranchCodeAndIdNot(String branchCode, Long id);



}
