package com.example.shopweb.controller;

import com.example.shopmodel.model.CategoryInfo;
import com.example.shopweb.mapper.CategoryScreenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class CategoryScreenController {

    @Autowired
    private CategoryScreenDao categoryScreenDao;

    @GetMapping("catepropie")
    public List<Map<String,Object>> selectCategory(){
        List<CategoryInfo> selectCategory = categoryScreenDao.selectCategory();
        List<Map<String,Object>> mapList= new ArrayList<>();
        for (CategoryInfo categoryInfo : selectCategory) {
            if(categoryInfo.getProduct_count()==0){
                continue;
            }
            Map<String,Object> map=new HashMap<>();
            map.put("name",categoryInfo.getCategory_name());
            map.put("value",categoryInfo.getProduct_count());
            mapList.add(map);
        }
        return mapList;
    }
}
