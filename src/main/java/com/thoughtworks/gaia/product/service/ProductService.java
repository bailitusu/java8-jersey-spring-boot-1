package com.thoughtworks.gaia.product.service;

import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.product.CommonMapper;
import com.thoughtworks.gaia.product.dao.ProductDao;
import com.thoughtworks.gaia.product.entity.Product;
import com.thoughtworks.gaia.product.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Transactional
public class ProductService implements Loggable {
    @Autowired
    private CommonMapper mapper;

    @Autowired
    private ProductDao productDao;

    public Product getProduct(Long productId) {
        ProductModel productModel = productDao.idEquals(productId).querySingle();
        if (productModel == null) {
            error("Product not found with id: " + productId);
            throw new NotFoundException();
        }

        return mapper.map(productModel, Product.class);
    }

    public Product create(Product product) {
        ProductModel productModel = mapper.map(product, ProductModel.class);
        productModel.setTimeCreated(new Date());
        productDao.save(productModel);
        return mapper.map(productModel, Product.class);
    }

    public Product update(Product product) {
        ProductModel productModel = mapper.map(product, ProductModel.class);
        productDao.update(productModel);
        return mapper.map(productModel, Product.class);
    }
}
