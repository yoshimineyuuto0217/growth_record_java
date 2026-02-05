package com.jobs.growth_record_java.auth.dto;

public class LoginResponse {
    
    private final String message;
    private final String token;

    public LoginResponse(String message,String token){
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
