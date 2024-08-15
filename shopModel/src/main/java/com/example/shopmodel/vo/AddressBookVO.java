package com.example.shopmodel.vo;

import com.example.shopmodel.serializer.IntegerToStringSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class AddressBookVO {
    @JsonSerialize(using = IntegerToStringSerializer.class)
    private Integer id;
    private Integer user_id;
    @JsonProperty("name")
    private String consignee;
    private String tel;
    private String address;
    private String label;
    @JsonProperty("isDefault")
    private boolean is_default;

}
