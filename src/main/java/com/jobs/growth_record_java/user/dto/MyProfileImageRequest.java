package com.jobs.growth_record_java.user.dto;

import org.springframework.web.multipart.MultipartFile;

public class MyProfileImageRequest {
    private MultipartFile profileImage;

    public MyProfileImageRequest(){}

    public MultipartFile getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartFile profileImage) { this.profileImage = profileImage; }
}
