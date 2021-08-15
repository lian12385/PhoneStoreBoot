package com.example.phonestore_boot.service;

import com.example.form.AddressForm;
import com.example.phonestore_boot.vo.address.AddressVo;

import java.util.List;

public interface AddressService {
    List<AddressVo> findAll();
    void saveOrUpdate(AddressForm addressForm);
}
