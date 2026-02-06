package com.jobs.growth_record_java.auth.dto;

public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest() {} 

    public String getEmail() {
        return email;
    }
    // 引数なくてもpasswordを使用できる理由は最初からpasswordをクラスに保持してるから
    public String getPassword() {
        return password;
    }

    
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
