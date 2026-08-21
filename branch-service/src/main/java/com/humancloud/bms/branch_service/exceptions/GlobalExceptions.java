package com.humancloud.bms.branch_service.exceptions;

import com.humancloud.bms.branch_service.enums.ErrorCodeEnums;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(BranchCodeAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleBranchCodeAlreadyExists(
            BranchCodeAlreadyExistsException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("httpStatusCode", ErrorCodeEnums.Conflict.getHttpStatusCode());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("httpStatusCode", ErrorCodeEnums.InternalServerError.getHttpStatusCode());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBranchNotFound(
            BranchNotFoundException ex) {

        Map<String, Object> body = new HashMap<>();

        body.put("code", ErrorCodeEnums.NotFound.getErrorCode());
        body.put("httpStatusCode", ErrorCodeEnums.NotFound.getErrorCode());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, Object> body = new HashMap<>();

        body.put("code", ErrorCodeEnums.BadRequest.getErrorCode());
        body.put("httpStatusCode", ErrorCodeEnums.BadRequest.getErrorCode());

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        body.put("message", "Validation failed");
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
