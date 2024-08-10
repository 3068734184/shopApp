package com.example.shopmodel.model;

import lombok.Data;

@Data
public class UserQuery {
    private Integer id;
    private String username;
    private String password;
    private String tel;
    private Integer status = 1;
}
