package com.humancloud.bms.employee_service.dto;

import com.humancloud.bms.employee_service.enums.ErrorCodeEnums;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        String  code,
        int     httpStatusCode,
        String  message,
        Map<String, String> errors,     // null unless it is a validation failure
        LocalDateTime timestamp
) {
    public static ErrorResponse of(ErrorCodeEnums e, String message) {
        return new ErrorResponse(e.getErrorCode(), e.getHttpStatusCode(),
                message, null, LocalDateTime.now());
    }

    public static ErrorResponse validation(Map<String, String> fieldErrors) {
        return new ErrorResponse(ErrorCodeEnums.BadRequest.getErrorCode(),
                ErrorCodeEnums.BadRequest.getHttpStatusCode(),
                "Validation failed", fieldErrors, LocalDateTime.now());
    }
}
