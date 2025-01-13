package com.kamilsmolarek.autofix.commons.errors;

public enum ErrorCode {
    USER_NOT_FOUND("User not found"),
    USER_NOT_LOGGED_IN("User is not logged in"),
    WRONG_LOGIN_OR_PASSWORD("Wrong login or password"),
    USER_IS_BLOCKED("User is blocked"),
    LINK_EXPIRED("Link expired"),
    VALIDATION_EXCEPTION("Invalid data provided"),
    UNKNOWN_ERROR("Something went wrong"),
    UNSUPPORTED_OPERATOR("Unsupported operator"),
    WORKSHOP_NOT_FOUND("Workshop not found."),
    EMPLOYEE_NOT_FOUND("Employee not found"),
    VEHICLE_NOT_FOUND("Vehicle not found"),
    SERVICE_NOT_FOUND("Service not found"),
    PAYMENT_NOT_FOUND("Payment not found"),
    BOOKING_NOT_FOUND("Booking not found"),
    USER_ALREADY_EMPLOYEE_OF_ANOTHER_WORKSHOP("User is already an employee of another workshop"),
    EMAIL_ALREADY_IN_USE("Adres email jest już zajęty."),
    TIME_SLOT_NOT_FOUND("Time slot not found"),;

    ErrorCode(String message) {
        this.message = message;
    }

    final String message;
}
