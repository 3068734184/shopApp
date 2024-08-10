package com.example.shopmodel.model;

import lombok.Data;

@Data
public class CategoryQuery {

    private Integer id;  //where id=#{id}
    private Integer[] ids; // where id in(1,2,3)
    private String name; //模糊查询
    private Integer status =1;
    private Integer parentId;
    //分页相关的
    private Integer pageNum;
    private Integer pageSize;


}
