package com.example.phonestore_boot.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private Integer specsId;
    private Integer phoneQuantity;
}
