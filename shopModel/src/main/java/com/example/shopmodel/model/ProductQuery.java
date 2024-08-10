package com.example.shopmodel.model;

import lombok.Data;

@Data
public class ProductQuery {
    private Integer id;
    private Integer[] ids;
    private String name;
    private Integer categoryId;
    private Integer status = 1;
    //分页相关
    private Integer pageNum;
    private Integer pageSize;
}
