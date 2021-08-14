package com.example.phonestore_boot.enums;

import lombok.Getter;

@Getter
public enum ResultEnums {
    PHONE_STOCK_ERROR(0,"手机库存不足");

    private Integer code;
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
