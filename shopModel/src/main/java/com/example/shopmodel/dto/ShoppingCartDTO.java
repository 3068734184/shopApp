package com.example.shopmodel.dto;


import lombok.Data;

@Data
public class ShoppingCartDTO {
    private Integer product_id;
    private Integer user_id;
    private Integer number = 1;
}
