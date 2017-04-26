package com.thoughtworks.gaia.product.service;

import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.product.CommonMapper;
import com.thoughtworks.gaia.product.dao.CategoryDao;
import com.thoughtworks.gaia.product.entity.Category;
import com.thoughtworks.gaia.product.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CategoryService implements Loggable {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private CategoryDao categoryDao;

    public Category getOne(Long categoryId) {
        return commonMapper.map(categoryDao.idEquals(categoryId).querySingle(), Category.class);
    }

    public Category save(Category category) {
        CategoryModel categoryModel = commonMapper.map(category, CategoryModel.class);
        categoryDao.save(categoryModel);
        return commonMapper.map(categoryModel, Category.class);
    }
}
