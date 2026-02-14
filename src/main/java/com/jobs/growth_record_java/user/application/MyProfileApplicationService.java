package com.jobs.growth_record_java.user.application;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.jobs.growth_record_java.user.domain.MyProfileDomainService;
import com.jobs.growth_record_java.user.dto.MyProfileImageResponse;
import com.jobs.growth_record_java.user.dto.MyprofileRequest;
import com.jobs.growth_record_java.user.dto.MyprofileResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;


@Service
public class MyProfileApplicationService {
    
    private final MyProfileDomainService myProfileDomainService;

    public MyProfileApplicationService(MyProfileDomainService myProfileDomainService){
        this.myProfileDomainService = myProfileDomainService;
    }
    //自分の情報の取得
    public MyprofileResponse me(){
        return myProfileDomainService.me();
    }
    //自分の情報を更新
    public MyprofileResponse patchMe(MyprofileRequest request ) {
        return myProfileDomainService.patchMe(request);
    }
    //自分の画像を更新
    public MyProfileImageResponse postProfileImage(@ModelAttribute MultipartFile request ) {
        return myProfileDomainService.postProfileImage(request);
    }
    //画像更新後に再取得するもの
    public ResponseEntity<Resource> getImage(String fileName) {
    Resource resource = myProfileDomainService.getImage(fileName);
    return ResponseEntity.ok().body(resource);
}

}
