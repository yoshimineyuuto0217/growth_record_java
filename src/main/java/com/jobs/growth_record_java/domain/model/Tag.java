package com.jobs.growth_record_java.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected Tag() {}

    public Tag(String tagName) {
        this.tagName = tagName;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTagName() { return tagName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
