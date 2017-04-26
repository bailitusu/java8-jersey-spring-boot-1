package com.thoughtworks.gaia.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class Tag {
    private Long id;
    private String content;

    private List<Article> articles;

    @JsonIgnoreProperties(value = "tags")
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
