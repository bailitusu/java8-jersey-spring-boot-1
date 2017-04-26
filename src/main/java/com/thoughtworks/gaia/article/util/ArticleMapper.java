package com.thoughtworks.gaia.article.util;

import com.thoughtworks.gaia.article.entity.Article;
import com.thoughtworks.gaia.article.model.ArticleModel;
import com.thoughtworks.gaia.article.model.TagModel;
import com.thoughtworks.gaia.article.repository.TagRepository;
import com.thoughtworks.gaia.common.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper extends ObjectMapper<ArticleModel, Article> {

    @Override
    public Article fromModel(ArticleModel model) {
        Article article = this.map(model, Article.class);
        if (!CollectionUtils.isEmpty(model.getTags())) {
            article.setTags(model.getTags().stream().map(TagModel::getContent).collect(Collectors.toList()));
        }
        return article;
    }

    @Override
    public ArticleModel fromDTO(Article article) {
        return map(article);
    }

    public ArticleModel map(Article article) {
        return this.map(article, ArticleModel.class);
    }

    @Override
    public List<ArticleModel> fromDTOList(List<Article> list) {
        return null;
    }

    @Override
    public List<Article> fromModelList(List<ArticleModel> list) {
        return null;
    }
}
