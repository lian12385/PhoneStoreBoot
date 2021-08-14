package com.example.phonestore_boot.service.impl;

import com.example.phonestore_boot.service.PhoneService;
import com.example.phonestore_boot.vo.DataVo;
import com.example.phonestore_boot.vo.PhoneInfoVo;
import com.example.phonestore_boot.vo.SpecsPackageVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PhoneServiceImplTest {

    @Autowired
    private PhoneService phoneService;

    @Test
    void findDataVo(){
        DataVo dataVo = phoneService.findDataVo();
        int id = 0;
    }

    @Test
    void findPhoneInfoVoByCategoryType(){
        List<PhoneInfoVo> list = phoneService.findPhoneInfoVoByCategoryType(2);
        int id = 2;
    }
    @Test
    void findSku(){
        SpecsPackageVo specsPackageVo = phoneService.findSpecsByPhoneId(1);
        int id = 3;
    }

    @Test
    void subStock(){
        phoneService.subStock(1,2);
    }


}