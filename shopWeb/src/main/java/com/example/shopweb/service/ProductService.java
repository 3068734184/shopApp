package com.example.shopweb.service;

import com.example.shopmodel.model.Product;
import com.example.shopmodel.model.ProductQuery;
import com.example.shopweb.annotaion.PageX;
import com.example.shopweb.mapper.ProductDao;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @PageX
    public Page<Product> selectProduct(ProductQuery productQuery) {
        return productDao.select(productQuery);
    }

    public int insertProduct(Product product) {
        return productDao.insetProduct(product);
    }

    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    public int softDeleteProduct(Integer id) {
        return productDao.softDelete(id, 0);
    }

    public int deleteProduct(Integer id) {
        return productDao.delete(id);
    }
}
