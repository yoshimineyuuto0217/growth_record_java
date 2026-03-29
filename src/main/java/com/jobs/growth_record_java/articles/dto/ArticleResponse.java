package com.jobs.growth_record_java.articles.dto;

import java.time.LocalDateTime;

public class ArticleResponse {

    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleResponse(Long articleId, String title, String content, LocalDateTime createdAt) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}