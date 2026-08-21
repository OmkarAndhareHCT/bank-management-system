package com.humancloud.bms.branch_service.dto;

import com.humancloud.bms.branch_service.entity.Branch;

public record BranchResponse(
        Long id, String branchCode, String address,
        String phone, String city, String state, String status
) {
    public static BranchResponse from(Branch b) {
        return new BranchResponse(b.getId(), b.getBranchCode(), b.getAddress(),
                b.getPhone(), b.getCity(), b.getState(), b.getStatus());
    }
}
