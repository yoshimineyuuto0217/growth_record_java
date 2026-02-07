package com.jobs.growth_record_java.user.application;

import org.springframework.stereotype.Service;

import com.jobs.growth_record_java.user.domain.MyProfileDomainService;
import com.jobs.growth_record_java.user.dto.MyprofileResponse;

@Service
public class MyProfileApplicationService {
    
    private final MyProfileDomainService myProfileDomainService;

    public MyProfileApplicationService(MyProfileDomainService myProfileDomainService){
        this.myProfileDomainService = myProfileDomainService;
    }
    public MyprofileResponse me(){
        return myProfileDomainService.me();
    }

}
