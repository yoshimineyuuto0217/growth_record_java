package com.jobs.growth_record_java.articles.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobs.growth_record_java.domain.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
  // 既存のタグ検索
    Optional<Tag> findByTagName(String tagName);
}