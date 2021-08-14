package com.example.phonestore_boot.exception;

import com.example.phonestore_boot.enums.ResultEnums;

public class PhoneException extends RuntimeException{
    public PhoneException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
    }
}
