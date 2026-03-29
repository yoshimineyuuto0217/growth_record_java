package com.jobs.growth_record_java.articles.application;

import org.springframework.stereotype.Service;

import com.jobs.growth_record_java.articles.domain.ArticleDomainService;
import com.jobs.growth_record_java.articles.dto.ArticleRequest;
import com.jobs.growth_record_java.articles.dto.ArticleResponse;

@Service
public class ArticleApplication {
    
    private final ArticleDomainService articleDomainService;

    public ArticleApplication(ArticleDomainService articleDomainService){
        this.articleDomainService = articleDomainService;
    }

    public ArticleResponse article(Long userId,ArticleRequest request){
        return articleDomainService.createArticle(userId,request);
    }
}
