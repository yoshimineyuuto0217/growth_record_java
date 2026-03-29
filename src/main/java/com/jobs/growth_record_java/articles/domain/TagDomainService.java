package com.jobs.growth_record_java.articles.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jobs.growth_record_java.articles.dto.TagResponse;
import com.jobs.growth_record_java.articles.repository.TagRepository;
import com.jobs.growth_record_java.domain.model.Tag;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class TagDomainService {
    
    private final TagRepository tagRepository;

    public TagDomainService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

     public TagResponse tag(List<String> tagNames){

        if (tagNames == null){
            return new TagResponse(List.of());
        }

        List<TagResponse.TagDto> savedTags = new ArrayList<>();

        for (String name : tagNames) {

            if (name == null || name.trim().isEmpty()) {
                continue;
            }

            String normalized = name.trim();

            Tag tag = tagRepository
                .findByTagName(normalized)
                .orElseGet(() -> {
                    Tag newTag = new Tag(normalized);
                    return tagRepository.save(newTag);
                });

            savedTags.add(
                new TagResponse.TagDto(
                    tag.getId(),
                    tag.getTagName()
                )
            );
        }

        return new TagResponse(savedTags);
    }
}
