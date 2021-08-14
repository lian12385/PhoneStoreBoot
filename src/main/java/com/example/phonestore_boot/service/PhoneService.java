package com.example.phonestore_boot.service;

import com.example.phonestore_boot.vo.DataVo;
import com.example.phonestore_boot.vo.PhoneInfoVo;
import com.example.phonestore_boot.vo.SpecsPackageVo;

import java.util.List;

public interface PhoneService {
    DataVo findDataVo();
    List<PhoneInfoVo> findPhoneInfoVoByCategoryType(Integer categoryType);
    SpecsPackageVo findSpecsByPhoneId(Integer phoneId);
    void subStock(Integer specsId, Integer quantity);
}
