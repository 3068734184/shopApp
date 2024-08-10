package com.example.shopmodel.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private int code;
    private String message; //如果code 不是0的时候 msg就是具体的报错信息
    private Object data;
    //返回正确数据
    public static ResponseDTO success(Object data){
        ResponseDTO dto=new ResponseDTO();
        dto.setCode(0);
        dto.setData(data);
        return dto;
    }


    //返回错误信息
    public static ResponseDTO error(int code,String message){
        ResponseDTO dto=new ResponseDTO();
        dto.setCode(code);
        dto.setMessage(message);
        return dto;
    }

}
