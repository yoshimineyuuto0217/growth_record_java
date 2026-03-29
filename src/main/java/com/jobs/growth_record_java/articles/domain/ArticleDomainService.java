package com.jobs.growth_record_java.articles.domain;

import org.springframework.stereotype.Service;

import com.jobs.growth_record_java.articles.dto.ArticleRequest;
import com.jobs.growth_record_java.articles.dto.ArticleResponse;
import com.jobs.growth_record_java.articles.repository.ArticleRepository;
import com.jobs.growth_record_java.domain.model.Article;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArticleDomainService {

    private final ArticleRepository articleRepository;

    public ArticleDomainService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public ArticleResponse createArticle(Long userId, ArticleRequest request){
    
        Article article = new Article(
            userId,
            request.getTitle(),
            request.getContent()
        );
    
        articleRepository.save(article);
    
        return new ArticleResponse(
            article.getArticleId(),
            article.getTitle(),
            article.getContent(),
            article.getCreatedAt()
        );
    }
}