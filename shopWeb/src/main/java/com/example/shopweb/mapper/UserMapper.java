package com.example.shopweb.mapper;

import com.example.shopmodel.model.User;
import com.example.shopmodel.model.UserQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user (username, tel, password, email, avatar, registerTime, status) values (#{username}, " +
            "#{tel}, #{password}, #{email}, #{avatar}, #{registerTime}, #{status});")
    int insertUser(User user);

    @Update("update user set username = #{username} ,tel = #{tel} ,password = #{password} ,email = #{email} ," +
            "avatar = #{avatar}  where id = #{id} ;")
    int updateUser(User user);

    @Update("update user set status = 0 where id = #{id} ;")
    int softDeleteUser(Integer id);

    // 模糊查询(username,tel,email,)
    List<User> fuzzySelectUser(User user);

    // 唯一查询
    UserQuery uniqueSelectUser(UserQuery userQuery);
}
