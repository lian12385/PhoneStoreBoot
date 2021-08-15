package com.example.phonestore_boot.service.impl;

import com.example.phonestore_boot.form.AddressForm;
import com.example.phonestore_boot.service.AddressService;
import com.example.phonestore_boot.vo.address.AddressVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    AddressService addressService;

    @Test
    void findAll(){
        List<AddressVo> addressVoList = addressService.findAll();
        int id = 0;
    }

    @Test
    void saveOrUpdate(){
        AddressForm addressForm = new AddressForm("张一一","13678900987","China","Guangdong","YangJiang","529800","168号306室");
        addressForm.setId(36);
        addressService.saveOrUpdate(addressForm);
        int id = 0;
    }

}