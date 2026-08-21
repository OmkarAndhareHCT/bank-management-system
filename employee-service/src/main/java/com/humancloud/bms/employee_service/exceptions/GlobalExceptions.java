package com.humancloud.bms.employee_service.exceptions;

import com.humancloud.bms.employee_service.enums.ErrorCodeEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptions {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(
            EmployeeNotFoundException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("code", ErrorCodeEnums.NotFound.getErrorCode());
        errors.put("message", ex.getMessage());
        errors.put("target", "Employee");

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBranchNotFound(
            BranchNotFoundException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("code", ErrorCodeEnums.UnprocessableEntity.getErrorCode());
        errors.put("message", ex.getMessage());
        errors.put("target", "Branch");

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicate(
            DuplicateEmailException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("code", ErrorCodeEnums.Conflict.getErrorCode());
        errors.put("message", ex.getMessage());
        errors.put("target", "Employee");

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BranchServiceUnavailableException.class)
    public ResponseEntity<Map<String, String>> handleUnavailable(
            BranchServiceUnavailableException ex) {

        log.error("Downstream failure", ex);

        Map<String, String> errors = new HashMap<>();
        errors.put("code", ErrorCodeEnums.ServiceUnavailable.getErrorCode());
        errors.put("message", ex.getMessage());
        errors.put("target", "Branch Service");

        return new ResponseEntity<>(errors, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
