package com.example.shopweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryCountDao {

    //分类的总数据
    @Select("SELECT COUNT(*) FROM category")
    int categoryCount();
    //当天添加的分类
    @Select("SELECT COUNT(*) FROM category  WHERE DATE(lastUpdateTime) = CURRENT_DATE")
    int categoryToday();
    //统计商品的总数据
    @Select("SELECT COUNT(*) FROM product")
    int productCount();

    //当天添加的商品数量
    @Select("SELECT COUNT(*) FROM product  WHERE DATE(lastUpdateTime) = CURRENT_DATE")
    int productToday();


}
