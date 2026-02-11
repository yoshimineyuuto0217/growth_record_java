package com.jobs.growth_record_java.user.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jobs.growth_record_java.user.application.MyProfileApplicationService;
import com.jobs.growth_record_java.user.dto.MyProfileImageRequest;
import com.jobs.growth_record_java.user.dto.MyProfileImageResponse;
import com.jobs.growth_record_java.user.dto.MyprofileRequest;
import com.jobs.growth_record_java.user.dto.MyprofileResponse;





@RestController
public class MyProfileController {
    private final MyProfileApplicationService myProfileApplicationService;

    public MyProfileController(MyProfileApplicationService myProfileApplicationService){
        this.myProfileApplicationService = myProfileApplicationService;
    }
    // ユーザー情報の取得
    @GetMapping("/me")
    public MyprofileResponse me(){
        return myProfileApplicationService.me();
    }
    // ユーザ-情報の更新
    @PatchMapping("/me")
    public MyprofileResponse patchMe(@RequestBody MyprofileRequest request){
        return myProfileApplicationService.patchMe(request);
    }
    // ユーザーの画像更新用
    @PostMapping(value = "/profile", consumes = "multipart/form-data")
    public MyProfileImageResponse uploadProfileImage(@ModelAttribute MyProfileImageRequest request) {
        MultipartFile file = request.getProfileImage();
        // 保存処理を書く
        return myProfileApplicationService.postProfileImage(file);
    }
    // ユーザーの画像取得用
    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
    
        Path path = Paths.get("upload/images/").resolve(fileName);
    
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }
    
        try {
            Resource resource = new UrlResource(path.toUri());
    
            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
    
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
    
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
}

}