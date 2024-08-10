package com.example.shopweb.controller;

import com.example.shopweb.service.ProductScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductScreenController {

    @Autowired
    private ProductScreenService productScreenService;

    @GetMapping("/categoryStock")
    public List<Map> selectCategoryStock() {
        return productScreenService.selectCategoryStock();
    }

    @GetMapping("bannerImages")
    public List<String> bannerImages() {
        return productScreenService.randomProductImages();
    }
}
