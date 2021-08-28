package com.example.phonestore_boot.handler;

import com.example.phonestore_boot.dto.OrderDto;
import com.example.phonestore_boot.exception.PhoneException;
import com.example.phonestore_boot.form.OrderForm;
import com.example.phonestore_boot.service.OrderService;
import com.example.phonestore_boot.util.ResultVoUtil;
import com.example.phonestore_boot.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping( "/order" )
public class OrderHandler {
    @Autowired
    private OrderService orderService;
    @PostMapping("create")
    public ResultVo create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数错误,orderForm={}",orderForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getTel());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setSpecsId(orderForm.getSpecsId());
        orderDto.setPhoneQuantity(orderForm.getQuantity());

        OrderDto result = orderService.create(orderDto);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());

        return ResultVoUtil.success(map);

    }

    @GetMapping("/detail/{orderId}")
    public ResultVo findOrderDetail(
            @PathVariable("orderId") String orderId){
        return ResultVoUtil.success(orderService.findOrderDetailByOrderId(orderId));
    }

    @PutMapping("/pay/{orderId}")
    public ResultVo pay(
            @PathVariable("orderId") String orderId){
        Map<String,String> map = new HashMap<>();
        map.put("orderID",orderService.pay(orderId));
        return ResultVoUtil.success(map);
    }
}
