package com.humancloud.bms.employee_service.dto;

public final class ValidationRules {
    public static final int NAME_MAX = 120;
    public static final int EMAIL_MAX = 120;
    public static final int PHONE_MAX = 20;
    public static final String PHONE_REGEX = "^[0-9+\\-\\s]{7,20}$";
}