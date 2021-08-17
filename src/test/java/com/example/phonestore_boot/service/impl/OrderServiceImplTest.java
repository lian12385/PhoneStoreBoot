package com.example.phonestore_boot.service.impl;

import com.example.phonestore_boot.dto.OrderDto;
import com.example.phonestore_boot.service.OrderService;
import com.example.phonestore_boot.vo.OrderDetailVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("zs");
        orderDto.setBuyerPhone("1234567890");
        orderDto.setBuyerAddress("广东省深圳市罗湖区科技路123号456室");
        orderDto.setSpecsId(1);
        orderDto.setPhoneQuantity(1);

        OrderDto result = orderService.create(orderDto);
        System.out.println(result);
    }

    @Test
    void findDetail(){
        OrderDetailVo orderDetailVo = orderService.findOrderDetailByOrderId("123456");
        int test = 1;
    }
    @Test
    void pay(){
        System.out.println(orderService.pay("123456"));
    }
}