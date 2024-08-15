package com.example.shopmodel.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingCartVO {
    private Integer id;
    private String name;
    private String img;
    private String brief;
    private Integer number;
    private BigDecimal unit_price;
    private BigDecimal amount;
}
