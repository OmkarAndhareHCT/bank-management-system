package com.humancloud.bms.branch_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BranchRequest(
        @NotBlank
        @Size(max = 20)
        String branchCode,

        @NotBlank
        @Size(max = 255)
        String address,

        @Pattern(regexp = "^[0-9+\\-\\s]{7,20}$", message = "invalid phone")
        String phone,

        @NotBlank @Size(max = 80)
        String city,

        @NotBlank @Size(max = 80)
        String state
) {}
