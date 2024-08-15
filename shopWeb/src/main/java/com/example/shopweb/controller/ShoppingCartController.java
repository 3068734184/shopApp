package com.example.shopweb.controller;

import com.example.shopmodel.dto.ShoppingCartDTO;
import com.example.shopmodel.vo.ShoppingCartVO;
import com.example.shopweb.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoppingCart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/selectByUserId")
    public List<ShoppingCartVO> selectShoppingCart(Integer user_id) {
        return shoppingCartService.selectShoppingCart(user_id);
    }

    @PostMapping("/addCart")
    public int insertShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return shoppingCartService.insertShoppingCart(shoppingCartDTO);
    }

    @DeleteMapping("/deleteCart")
    public int softDeleteShoppingCart(Integer id) {
        return shoppingCartService.softDeleteShoppingCart(id);
    }
}
