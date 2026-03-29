package com.jobs.growth_record_java.articles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobs.growth_record_java.domain.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByUserId(Long userId);

}
