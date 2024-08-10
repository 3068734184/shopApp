package com.example.shopweb.mapper;

import com.example.shopmodel.model.Category;
import com.example.shopmodel.model.CategoryQuery;
import com.example.shopweb.annotaion.PageX;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryDao {
    //分页查询
    Page<Category> select(CategoryQuery query);

    @Insert("insert into category (name, img, seq, parentId, lastUpdateBy) values (#{name} ,#{img} ,#{seq} " +
            ",#{parentId} ,#{lastUpdateBy} );")
    int insert(Category category);

    @Update("update category set name = #{name} ,img = #{img} ,seq = #{seq} ,parentId =#{parentId} " +
            ",lastUpdateBy = #{lastUpdateBy}  where id = #{id} ;")
    int update(Category category);

    @Update("update category set status = #{status}  where id = #{id};")
    int softDelete(@Param("id") Integer id, @Param("status") Integer status);

    // 查询父分类
    @Select("select * from category where parentId = 0 and status = 1")
    List<Category> selectParent();

    // 查询子分类
    @Select("select * from category where parentId !=0 and status = 1;")
    List<Category> selectChild();
}
