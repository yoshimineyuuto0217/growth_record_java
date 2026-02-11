package com.jobs.growth_record_java.user.dto;


public class MyProfileImageResponse {
    private final String profile_image;

    public MyProfileImageResponse (String profile_image){
        this.profile_image = profile_image;
    }
    public String getProfile_image() {
        return profile_image;
    }

}
