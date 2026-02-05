package com.jobs.growth_record_java.auth.dto;

public class RegisterResponse {

    private final String message;

    public RegisterResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
