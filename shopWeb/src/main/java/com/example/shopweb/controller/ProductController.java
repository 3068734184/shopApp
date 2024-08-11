package com.example.shopweb.controller;

import com.example.shopmodel.model.Product;
import com.example.shopmodel.model.ProductQuery;
import com.example.shopweb.service.ProductService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<Product> selectProduct(ProductQuery productQuery) {
        return productService.selectProduct(productQuery);
    }

    @GetMapping("/byId")
    public Product selectProductById(Integer id) {
        return productService.selectProductById(id);
    }

    @PostMapping
    public int insertProduct(@RequestBody Product product) {
        return productService.insertProduct(product);
    }

    @PutMapping
    public int updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping
    public int softDeleteProduct(Integer id) {
        return productService.softDeleteProduct(id);
    }
}
