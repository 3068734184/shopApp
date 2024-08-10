package com.example.shopweb.controller;

import com.example.shopmodel.mCategory.Item;
import com.example.shopmodel.model.Category;
import com.example.shopmodel.model.CategoryQuery;
import com.example.shopweb.service.CategoryService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Page<Category> select(CategoryQuery query){
        return categoryService.select(query);
    }
    @PostMapping
    public int insert(@RequestBody Category category){
        return categoryService.insert(category);
    }

    @PutMapping
    public int update(@RequestBody Category category){
        return categoryService.update(category);
    }
    @DeleteMapping
    public int delete(Integer id){
        return categoryService.softDelete(id);
    }

    @GetMapping("/MSelect")
    public List<Item> MSelect() {
        return categoryService.selectMCategory();
    }

}
