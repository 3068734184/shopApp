package com.example.shopmodel.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ShoppingCart {
    private Integer id;
    private String name;
    private String img;
    private Integer user_id;
    private Integer category_id;
    private Integer product_id;
    private String brief;
    private Integer number;
    private BigDecimal unit_price;
    private BigDecimal amount;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private boolean is_delete;
}
