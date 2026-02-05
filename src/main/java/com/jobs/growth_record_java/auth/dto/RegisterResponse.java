package com.jobs.growth_record_java.auth.dto;

public class RegisterResponse {

    private final String message;
    private final String token;

    public RegisterResponse(String message,String token) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }


    public String getMessage() {
        return message;
    }
}
