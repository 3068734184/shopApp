package com.example.shopmodel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String img;
    private Integer status;
    private BigDecimal price;
    private String brief;
    private Integer seq;
    private String lastUpdateBy;
    private LocalDateTime lastUpdateTime;
}
