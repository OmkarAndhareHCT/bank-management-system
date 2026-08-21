package com.humancloud.bms.employee_service.dto;

import com.humancloud.bms.employee_service.enums.Role;
import jakarta.validation.constraints.*;

public record EmployeeUpdateRequest(

        @NotBlank(message = "Name is required")
        @Size(max = ValidationRules.NAME_MAX)
        String name,

        @Size(max = ValidationRules.PHONE_MAX)
        @Pattern(regexp = ValidationRules.PHONE_REGEX, message = "Invalid phone")
        String phone,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        @Size(max = ValidationRules.EMAIL_MAX)
        String email,

        @NotNull(message = "Role is required")
        Role role,

        @NotNull(message = "Branch is required")
        @Positive
        Long branchId
) {}
