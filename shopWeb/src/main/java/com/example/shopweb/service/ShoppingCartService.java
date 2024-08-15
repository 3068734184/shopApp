package com.example.shopweb.service;

import com.example.shopmodel.dto.ShoppingCartDTO;
import com.example.shopmodel.model.Product;
import com.example.shopmodel.model.ShoppingCart;
import com.example.shopmodel.vo.ShoppingCartVO;
import com.example.shopweb.mapper.ShoppingCartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShoppingCartService {
    private final ShoppingCartMapper shoppingCartMapper;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartMapper shoppingCartMapper, ProductService productService) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.productService = productService;
    }

    public List<ShoppingCartVO> selectShoppingCart(Integer user_id) {
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectShoppingCart(user_id);
        ArrayList<ShoppingCartVO> shoppingCartVOS = new ArrayList<>();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
            BeanUtils.copyProperties(shoppingCart, shoppingCartVO);
            shoppingCartVOS.add(shoppingCartVO);
        }
        return shoppingCartVOS;
    }

    @Transactional
    public int insertShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Product product = productService.selectProductById(shoppingCartDTO.getProduct_id());
        ShoppingCart existCart = shoppingCartMapper.existCart(shoppingCartDTO.getProduct_id());
        if (existCart == null) {
            shoppingCart.setName(product.getName());
            shoppingCart.setImg(product.getImg());
            shoppingCart.setCategory_id(product.getCategoryId());
            shoppingCart.setProduct_id(shoppingCartDTO.getProduct_id());
            shoppingCart.setBrief(product.getBrief());
            shoppingCart.setUnit_price(product.getPrice());
            shoppingCart.setAmount(product.getPrice().multiply(BigDecimal.valueOf(shoppingCartDTO.getNumber())));
            shoppingCart.setCreate_time(LocalDateTime.now());
            shoppingCart.setUpdate_time(LocalDateTime.now());
            shoppingCart.set_delete(false);
            log.info("...shoppingCart{}", shoppingCart);
            return shoppingCartMapper.insertShoppingCart(shoppingCart);
        } else {
            // 更新数量和金额
            existCart.setNumber(existCart.getNumber() + 1);
            existCart.setAmount(existCart.getAmount().add(product.getPrice()));
            existCart.setUpdate_time(LocalDateTime.now());
            log.info("...existCart{}",existCart);
            return shoppingCartMapper.updateNumberAndAmount(existCart);
        }

    }

    public int softDeleteShoppingCart(Integer id) {
        return shoppingCartMapper.softDeleteShoppingCart(id);
    }
}
