package com.thoughtworks.gaia.article.repository;

import com.thoughtworks.gaia.article.model.ArticleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleModel, Long> {
}
