package com.jobs.growth_record_java.articles.application;

import org.springframework.stereotype.Service;

import com.jobs.growth_record_java.articles.domain.TagDomainService;
import com.jobs.growth_record_java.articles.dto.TagRequest;
import com.jobs.growth_record_java.articles.dto.TagResponse;

@Service
public class TagApplication {
    
    private final TagDomainService tagDomainService;

    public TagApplication(TagDomainService tagDomainService) {
        this.tagDomainService = tagDomainService;
    }

    public TagResponse tag(TagRequest request){
        return tagDomainService.tag(request.getTagNames());
    }
}
