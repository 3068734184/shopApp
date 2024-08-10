package com.example.shopmodel.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String username;
    private String tel;
    private String password;
    private String email;
    private String avatar;
    private LocalDateTime registerTime;
    private Integer status = 1;
}
