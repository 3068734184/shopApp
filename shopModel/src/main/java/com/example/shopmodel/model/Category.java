package com.example.shopmodel.model;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {

    private Integer id;
    private String name;
    private String img;
    @Alias("排序")
    private Integer seq;
    @Alias("父类ID")
    private Integer parentId;
    private Integer status;
    @Alias("最近修改人")
    private String lastUpdateBy;
    @Alias("最近更新时间")
    private LocalDateTime lastUpdateTime;

}
