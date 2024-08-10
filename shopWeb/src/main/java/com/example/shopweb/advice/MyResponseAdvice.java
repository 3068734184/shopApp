package com.example.shopweb.advice;

import cn.hutool.json.JSONUtil;
import com.example.shopmodel.dto.ResponseDTO;
import com.example.shopmodel.exception.MyException;
import com.example.shopmodel.info.PageInfo;
import com.example.shopmodel.utils.LocalpageInfoUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice  //增强器
public class MyResponseAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
       //哪些方法需要增强
        return true;
    }

    //错误的信息
    @ExceptionHandler
    public Object processException(Exception ex){
        if(ex instanceof MyException){
            MyException myException= (MyException) ex;
            ex.printStackTrace();
            return ResponseDTO.error(myException.getCode(), ex.getMessage());
        }
        ex.printStackTrace();
        return ResponseDTO.error(808, ex.getMessage());
    }

    @Override
    public Object beforeBodyWrite(
            Object bady,
            MethodParameter methodParameter,
            MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> aClass,
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse) {

       //解决双层包括的错误信息
       if(bady instanceof ResponseDTO){
           return bady;
       }
        ResponseDTO dto=ResponseDTO.success(bady);
       //获取对象 线程上的标签
        PageInfo pageInfo= LocalpageInfoUtil.get();
        if(pageInfo != null){
            Map<Object,Object> map=new HashMap<>();
            map.put("total",pageInfo.getTotal());
            map.put("item",bady);
            dto=ResponseDTO.success(map);
            //删除
            LocalpageInfoUtil.remove();
        }


        //匹配接口类型
        if(aClass == StringHttpMessageConverter.class){
            //改成json字符串
            return JSONUtil.toJsonStr(dto);
        }

        return dto;
    }
}
