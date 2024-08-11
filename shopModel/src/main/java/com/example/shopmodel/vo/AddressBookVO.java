package com.example.shopmodel.vo;

import lombok.Data;

@Data
public class AddressBookVO {
    private Integer id;
    private Integer user_id;
    private String consignee;
    private String tel;
    private String address;
    private String label;
    private String is_default;

}
