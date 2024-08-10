package com.example.shopweb.controller;

import com.example.shopweb.service.CategoryCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CategoryCountController {
    @Autowired
    private CategoryCountService categoryCountService;

    @GetMapping("cateCount")
    public int categoryCount(){
        return  categoryCountService.categoryCount();
    }
    //当天添加的分类
    @GetMapping("todayCount")
    public int categoryToday(){
        return categoryCountService.categoryToday();
    }
    //统计商品的总数据
    @GetMapping("proCount")
    public int productCount(){
        return  categoryCountService.productCount();
    }

    //当天添加的商品数量
    @GetMapping("proTodayCount")
    public int productToday(){
        return categoryCountService.productToday();
    }
}
