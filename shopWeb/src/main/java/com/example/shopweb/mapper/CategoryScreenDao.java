package com.example.shopweb.mapper;

import com.example.shopmodel.model.CategoryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryScreenDao {

    @Select("select c.name as category_name ,COUNT(p.id) as product_count " +
            "FROM category c LEFT JOIN product p " +
            "on c.id=p.categoryId where c.status= 1 GROUP BY c.id,c.name")
    List<CategoryInfo> selectCategory();


}
