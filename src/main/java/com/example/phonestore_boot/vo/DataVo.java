package com.example.phonestore_boot.vo;

import lombok.Data;

import java.util.List;

@Data
public class DataVo {
    private List<PhoneCategoryVo> categories;
    private List<PhoneInfoVo> phones;
}
