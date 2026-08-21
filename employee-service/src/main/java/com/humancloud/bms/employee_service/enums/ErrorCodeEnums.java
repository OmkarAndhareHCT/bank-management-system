package com.humancloud.bms.employee_service.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnums {

    BadRequest(400, "BadRequest"),
    NotFound(404, "NotFound"),
    Conflict(409, "Conflict"),
    UnprocessableEntity(422, "UnprocessableEntity"),
    ServiceUnavailable(503, "ServiceUnavailable"),
    InternalServerError(500, "InternalServerError"),
    ;

    private final int httpStatusCode;
    private final String errorCode;

    ErrorCodeEnums(int httpStatusCode, String errorCode) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
    }
}
