package com.example.phonestore_boot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.math.BigDecimal;

@Data
public class OrderDetailVo {
    private String orderId;
    private String buyerName;
    @JsonProperty("tel")
    private String buyerPhone;
    @JsonProperty("address")
    private String buyerAddress;
    @JsonProperty("num")
    private Integer phoneQuantity;
    private String phoneName;
    private String specsPrice;
    @JsonProperty("icon")
    private String phoneIcon;
    @JsonProperty("amount")
    private BigDecimal orderAmount;
    private Integer payStatus;
    private Integer freight = 10;
}

