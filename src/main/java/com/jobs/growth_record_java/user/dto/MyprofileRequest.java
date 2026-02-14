package com.jobs.growth_record_java.user.dto;

public class MyprofileRequest {
    private String name;
    private String self_introduction;

    public MyprofileRequest(){}

    public String getName() {
        return name;
    }


    public String getSelf_introduction() {
        return self_introduction;
    }
    public void setName(String name) { this.name = name; }
    public void setSelf_introduction(String self_introduction) { this.self_introduction = self_introduction; }
}
