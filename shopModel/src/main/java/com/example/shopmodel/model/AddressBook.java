package com.example.shopmodel.model;

import lombok.Data;

@Data
public class AddressBook {
    private Integer id;
    private Integer user_id;
    private String consignee;
    private String tel;
    private String province_name;
    private String city_name;
    private String district_name;
    private String detail;
    private String label;
    private String is_default;
}
