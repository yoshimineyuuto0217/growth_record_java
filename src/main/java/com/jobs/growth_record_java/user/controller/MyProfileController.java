package com.jobs.growth_record_java.user.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobs.growth_record_java.user.application.MyProfileApplicationService;
import com.jobs.growth_record_java.user.dto.MyprofileResponse;

@RestController
public class MyProfileController {
    private final MyProfileApplicationService myProfileApplicationService;

    public MyProfileController(MyProfileApplicationService myProfileApplicationService){
        this.myProfileApplicationService = myProfileApplicationService;
    }
    // ユーザー情報
    @GetMapping("/me")
    public MyprofileResponse me(){
        return myProfileApplicationService.me();
    }
}