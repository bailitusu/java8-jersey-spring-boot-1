package com.thoughtworks.gaia.product.dao;

import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import com.thoughtworks.gaia.product.model.CategoryModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryDao extends BaseDaoWrapper<CategoryModel> {
    public CategoryDao() {
        super(CategoryModel.class);
    }
}
