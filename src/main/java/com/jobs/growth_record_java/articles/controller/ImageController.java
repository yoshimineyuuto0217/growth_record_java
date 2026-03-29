package com.jobs.growth_record_java.articles.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jobs.growth_record_java.articles.application.ImageApplication;
import com.jobs.growth_record_java.articles.dto.ImagePresignedUrlRequest;

@RestController
@RequestMapping("/article_images")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class ImageController {

    private final ImageApplication imageApplication;

    public ImageController(ImageApplication imageApplication) {
        this.imageApplication = imageApplication;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> upload(
            @RequestParam("file") MultipartFile file
    ) {
        String url = imageApplication.upload(file);
        return Map.of("url", url);
    }

    @PostMapping("/presigned")
    public Map<String, String> createPresignedUrl(
            @RequestBody ImagePresignedUrlRequest request
    ) {
        String url = imageApplication.createPresignedUploadUrl(
            request.getFileName(),
            request.getContentType()
        );
        return Map.of("url", url);
    }

    @PostMapping(value = "/s3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> uploadToS3(
            @RequestParam("file") MultipartFile file
    ) {
        String url = imageApplication.uploadToS3(file);
        return Map.of("url", url);
    }
}