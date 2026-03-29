package com.jobs.growth_record_java.articles.dto;

import jakarta.validation.constraints.NotBlank;

public class ArticleRequest {

    @NotBlank(message = "タイトルは必須です")
    private String title;

    @NotBlank(message = "本文は必須です")
    private String content;

    public ArticleRequest() {}

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}