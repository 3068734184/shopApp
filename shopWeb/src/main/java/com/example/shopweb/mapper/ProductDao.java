package com.example.shopweb.mapper;

import com.example.shopmodel.model.Product;
import com.example.shopmodel.model.ProductQuery;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductDao {
    //分页查询
    Page<Product> select(ProductQuery productQuery);

    @Insert("insert into product (name, categoryId, img, price, brief, lastUpdateBy) values (#{name} ,#{categoryId} " +
            ",#{img} ,#{price} ,#{brief} ,#{lastUpdateBy} );")
    int insetProduct(Product product);

    @Update("update product set name = #{name} ,categoryId=#{categoryId} ,img=#{img} ,price=#{price} ,brief=#{brief} " +
            " where id = #{id} ;")
    int updateProduct(Product product);

    @Update("update product set status = #{status} where id = #{id} ")
    int softDelete(@Param("id") Integer id,@Param("status") Integer status);

    @Delete("delete from product where id = #{id} ;")
    int delete(Integer id);
}
