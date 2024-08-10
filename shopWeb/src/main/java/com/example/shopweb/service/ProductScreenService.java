package com.example.shopweb.service;

import com.example.shopweb.mapper.ProductScreenDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProductScreenService {
    @Autowired
    private ProductScreenDao productScreenDao;

    public List<Map> selectCategoryStock() {
        return productScreenDao.selectCategoryStock();
    }

    public List<String> randomProductImages() {
        return productScreenDao.randomProductImages();
    }
}
