package com.jobs.growth_record_java.articles.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jobs.growth_record_java.articles.domain.ImageDomainService;

@Service
public class ImageApplication {

    private final ImageDomainService imageDomainService;

    public ImageApplication(ImageDomainService imageDomainService) {
        this.imageDomainService = imageDomainService;
    }

    public String upload(MultipartFile file) {
        return imageDomainService.saveImage(file);
    }

    public String createPresignedUploadUrl(String fileName, String contentType) {
        return imageDomainService.generatePresignedUploadUrl(fileName, contentType);
    }

    public String uploadToS3(MultipartFile file) {
        return imageDomainService.uploadToS3(file);
    }
}