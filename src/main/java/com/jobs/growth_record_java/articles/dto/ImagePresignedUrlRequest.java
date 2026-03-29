package com.jobs.growth_record_java.articles.dto;

import jakarta.validation.constraints.NotBlank;

public class ImagePresignedUrlRequest {

    @NotBlank(message = "ファイル名は必須です")
    private String fileName;

    @NotBlank(message = "コンテンツタイプは必須です")
    private String contentType;

    public ImagePresignedUrlRequest() {}

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }
}

