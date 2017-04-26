package com.thoughtworks.gaia.article.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class TagModel extends IdBaseModel {

    @Column(name = "content")
    private String content;

    @ManyToMany(mappedBy = "tags")
    private List<ArticleModel> articles;

    public TagModel() {
    }

    public TagModel(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }
}
