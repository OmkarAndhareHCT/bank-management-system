package com.humancloud.bms.branch_service.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public enum ErrorCodeEnums {

    InternalServerError(500, "InternalServerError"),
    Conflict(409, "Conflict"),
    NotFound(404, "NotFound"),
    BadRequest(400, "BadRequest"),
    ;


    private final int httpStatusCode;
    private final String errorCode;

    ErrorCodeEnums(int httpStatusCode, String errorCode) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
    }
}
