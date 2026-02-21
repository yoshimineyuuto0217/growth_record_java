package com.jobs.growth_record_java.articles.dto;

import java.util.ArrayList;
import java.util.List;


public class TagRequest {

    private List<String> tagNames;

    // インスタンスするために必要
    public TagRequest(){}

    public List<String> getTagNames(){
        return tagNames;
    }
    
    public void setTagNames(List<String> tagNames) {
    this.tagNames = tagNames != null ? tagNames : new ArrayList<>(); }
}
