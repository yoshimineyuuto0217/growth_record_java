package com.jobs.growth_record_java.user.dto;

public class MyprofileResponse {
    
    private final String name;
    private final String profile_image;
    private final String self_introduction;

    public MyprofileResponse(String name,String profile_image,String self_introduction){
        this.name = name;
        this.profile_image = profile_image;
        this.self_introduction = self_introduction;
    }
    
    public String getName() {
        return name;
    }
    public String getProfile_image() {
        return profile_image;
    }
    public String getSelf_introduction() {
        return self_introduction;
    }
}
