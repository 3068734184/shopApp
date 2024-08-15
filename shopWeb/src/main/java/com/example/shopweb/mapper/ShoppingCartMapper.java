package com.example.shopweb.mapper;

import com.example.shopmodel.model.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    // 根据用户id查询
    @Select("select id,name,img,category_id,product_id,brief,number,unit_price,amount from shopping_cart where " +
            "user_id = #{user_id} and is_delete = 0;")
    List<ShoppingCart> selectShoppingCart(Integer user_id);

    @Select("select * from shopping_cart where product_id = #{product_id} and is_delete = 0;")
    ShoppingCart existCart(Integer product_id);

    @Insert("insert into shopping_cart (name, img, user_id, category_id, product_id, brief, number, unit_price, amount, " +
            "create_time, update_time, is_delete) values (#{name} ,#{img} ,#{user_id} ,#{category_id} ,#{product_id} " +
            ",#{brief} ,#{number}, #{unit_price}  ,#{amount} ,#{create_time} ,#{update_time} ,#{is_delete} );")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertShoppingCart(ShoppingCart shoppingCart);

    @Update("update shopping_cart set number = #{number} ,amount = #{amount} ,update_time = #{update_time}  " +
            "where id = #{id} ;")
    int updateNumberAndAmount(ShoppingCart shoppingCart);

    // 逻辑删除
    @Delete("delete from shopping_cart where id = #{id} ;")
    int softDeleteShoppingCart(Integer id);
}
