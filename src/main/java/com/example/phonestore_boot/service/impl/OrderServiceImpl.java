package com.example.phonestore_boot.service.impl;

import com.example.phonestore_boot.dto.OrderDto;
import com.example.phonestore_boot.entity.OrderMaster;
import com.example.phonestore_boot.entity.PhoneInfo;
import com.example.phonestore_boot.entity.PhoneSpecs;
import com.example.phonestore_boot.enums.PayStatusEnum;
import com.example.phonestore_boot.enums.ResultEnums;
import com.example.phonestore_boot.exception.PhoneException;
import com.example.phonestore_boot.repository.OrderMasterRepository;
import com.example.phonestore_boot.repository.PhoneInfoRepository;
import com.example.phonestore_boot.repository.PhoneSpecsRepository;
import com.example.phonestore_boot.service.OrderService;
import com.example.phonestore_boot.service.PhoneService;
import com.example.phonestore_boot.util.KeyUtil;
import com.example.phonestore_boot.vo.OrderDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;
    @Autowired
    private PhoneInfoRepository phoneInfoRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PhoneService phoneService;
    @Override
    public OrderDto create(OrderDto orderDto){
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);

        PhoneSpecs phoneSpecs =phoneSpecsRepository.findById(orderDto.getSpecsId()).get();
        if(phoneSpecs == null){
            log.error("【创建订单】规格为空,phoneSpecs={}",phoneSpecs);
            throw new PhoneException(ResultEnums.SPECS_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneSpecs,orderMaster);

        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        if(phoneInfo == null){
            log.error("【创建订单】手机为空,phoneInfo={}",phoneInfo);
            throw new PhoneException(ResultEnums.PHONE_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneInfo,orderMaster);


        //计算总价
        BigDecimal orderAmount = new BigDecimal(0);
        orderAmount = phoneSpecs.getSpecsPrice()
                .divide(new BigDecimal(100))
                .multiply(new BigDecimal(orderDto.getPhoneQuantity())
                .add(orderAmount))
                .add(new BigDecimal(10));
        orderMaster.setOrderAmount(orderAmount);

        //orderId
        orderMaster.setOrderId(KeyUtil.createUniqueKey());
        orderDto.setOrderId(orderMaster.getOrderId());

        //payStatus
        orderMaster.setPayStatus(PayStatusEnum.UNPAID.getCode());

        orderMasterRepository.save(orderMaster);

        phoneService.subStock(orderDto.getSpecsId(),orderDto.getPhoneQuantity());

        return orderDto;
    }

    @Override
    public OrderDetailVo findOrderDetailByOrderId(String orderId) {
        OrderDetailVo orderDetailVo = new OrderDetailVo();

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if(orderMaster == null){
            log.error("【查询订单】订单不存在，orderMaster={}",orderMaster);
            throw new PhoneException(ResultEnums.ORDER_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster, orderDetailVo);
        String a = (new BigDecimal(100))+".00";
        System.out.println((new BigDecimal(100))+".00".getClass().getName());
        orderDetailVo.setSpecsPrice(orderMaster.getSpecsPrice().divide(new BigDecimal(100))+".00");
        return orderDetailVo;
    }

    @Override
    public String pay(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();

        if(orderMaster == null){
            log.error("【支付订单】订单不存在，orderMaster={}",orderMaster);
            throw new PhoneException(ResultEnums.ORDER_NOT_EXIST);
        }
        if(orderMaster.getPayStatus().equals(PayStatusEnum.UNPAID.getCode())){
            orderMaster.setPayStatus(PayStatusEnum.PAID.getCode());
            orderMasterRepository.save(orderMaster);
        }
        else{
            log.info("【支付订单】订单已支付，orderMaster={}",orderMaster);
        }
        return orderId;
    }
}
