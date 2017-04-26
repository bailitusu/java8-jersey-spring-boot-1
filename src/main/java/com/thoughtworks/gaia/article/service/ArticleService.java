package com.thoughtworks.gaia.article.service;

import com.thoughtworks.gaia.article.entity.Article;
import com.thoughtworks.gaia.article.model.ArticleModel;
import com.thoughtworks.gaia.article.model.TagModel;
import com.thoughtworks.gaia.article.repository.ArticleRepository;
import com.thoughtworks.gaia.article.util.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    TagService tagService;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleRepository articleRepository;

    public Article get(Long articleId) {
        ArticleModel articleModel = articleRepository.findOne(articleId);
        return articleModel != null ? articleMapper.fromModel(articleModel) : null;
    }

    public Article create(Article article) {
        ArticleModel model = articleMapper.map(article);
        List<TagModel> tagModels = tagService.createIfNotExist(article.getTags());
        model.setTags(tagModels);
        articleRepository.save(model);
        return articleMapper.fromModel(model);
    }
}
