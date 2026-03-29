package com.jobs.growth_record_java.articles.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobs.growth_record_java.articles.application.ArticleApplication;
import com.jobs.growth_record_java.articles.dto.ArticleRequest;
import com.jobs.growth_record_java.articles.dto.ArticleResponse;
import com.jobs.growth_record_java.security.CustomUserDetails;

@RestController
public class ArticleController {
    
    private final ArticleApplication articleApplication;

    public ArticleController(ArticleApplication articleApplication){
        this.articleApplication = articleApplication;
    }
    @PostMapping("/article")
        public ArticleResponse tag(@AuthenticationPrincipal CustomUserDetails user,@RequestBody ArticleRequest request){
        return articleApplication.article(user.getUserId(),request);
    }
}
