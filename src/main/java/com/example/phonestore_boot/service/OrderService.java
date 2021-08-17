package com.example.phonestore_boot.service;

import com.example.phonestore_boot.dto.OrderDto;
import com.example.phonestore_boot.vo.OrderDetailVo;

public interface OrderService {
    OrderDto create(OrderDto orderDto);
    OrderDetailVo findOrderDetailByOrderId(String orderId);
    String pay(String orderId);
}
