package com.thoughtworks.gaia.article.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "article")
public class ArticleModel extends IdBaseModel {

    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;

    @ManyToMany(targetEntity = TagModel.class, fetch = FetchType.EAGER)
    @JoinTable(name = "article_tags",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagModel> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }
}
