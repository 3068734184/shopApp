package com.example.shopweb.service;

import com.example.shopweb.mapper.CategoryCountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryCountService {
    @Autowired
    private CategoryCountDao categoryCountDao;

    public int categoryCount(){
        return  categoryCountDao.categoryCount();
    }
    //当天添加的分类
    public int categoryToday(){
        return categoryCountDao.categoryToday();
    }
    //统计商品的总数据
    public int productCount(){
        return  categoryCountDao.productCount();
    }

    //当天添加的商品数量
    public int productToday(){
        return categoryCountDao.productToday();
    }

}
