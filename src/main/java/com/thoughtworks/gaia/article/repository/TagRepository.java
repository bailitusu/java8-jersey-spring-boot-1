package com.thoughtworks.gaia.article.repository;

import com.thoughtworks.gaia.article.model.ArticleModel;
import com.thoughtworks.gaia.article.model.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagModel, Long> {

    TagModel findOneByContent(String content);

    List<TagModel> findAllByContentIn(List<String> contents);
}
