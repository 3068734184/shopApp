package com.example.shopweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductScreenDao {
    @Select("SELECT c.`name` AS categoryName,SUM(p.stock) as stock FROM category c LEFT JOIN product p on " +
            "p.categoryId = c.id WHERE c.`status` = 1 GROUP BY c.`name`")
    List<Map> selectCategoryStock();

    @Select("SELECT img FROM `product` WHERE img <> '' ORDER BY RAND() LIMIT 3")
    List<String> randomProductImages();
}
