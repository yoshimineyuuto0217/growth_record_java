package com.jobs.growth_record_java.articles.dto;

import java.util.List;

public class TagResponse {

    private final List<TagDto> tags;

    public TagResponse(List<TagDto> tags) {
        this.tags = tags;
    }

    public List<TagDto> getTagName() {
        return tags;
    }

    // フロントに返すために必要
    public static class TagDto {

        private final Long id;
        private final String name;

        public TagDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getTagName() {
            return name;
        }
    }
}
