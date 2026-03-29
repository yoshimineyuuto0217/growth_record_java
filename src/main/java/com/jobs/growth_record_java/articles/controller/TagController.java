package com.jobs.growth_record_java.articles.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobs.growth_record_java.articles.application.TagApplication;
import com.jobs.growth_record_java.articles.dto.TagRequest;
import com.jobs.growth_record_java.articles.dto.TagResponse;

@RestController
public class TagController {
    
    private final TagApplication tagApplication;

    public TagController(TagApplication tagApplication){
        this.tagApplication = tagApplication;
    }

    @PostMapping("/tag")
    public TagResponse tag(@RequestBody TagRequest request){
        return tagApplication.tag(request);
    }
}
