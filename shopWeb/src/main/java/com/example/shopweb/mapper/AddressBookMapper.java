package com.example.shopweb.mapper;

import com.example.shopmodel.model.AddressBook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    @Insert("INSERT INTO address_book (user_id, consignee, tel, province_name, city_name, district_name, detail, label, is_default) " +
            "VALUES (#{user_id}, #{consignee}, #{tel}, #{province_name}, #{city_name}, #{district_name}, #{detail}, #{label}, #{is_default})")
    int insertAddressBook(AddressBook addressBook);

    @Select("SELECT * FROM address_book WHERE id=#{id}")
    AddressBook selectAddressBookById(Integer id);

    @Select("SELECT * FROM address_book WHERE user_id=#{userId}")
    List<AddressBook> selectAddressBooksByUserId(Integer userId);

    @Delete("DELETE FROM address_book WHERE id=#{id}")
    int deleteAddressBook(Integer id);
}