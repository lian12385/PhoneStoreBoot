package com.example.phonestore_boot.service.impl;

import com.example.phonestore_boot.form.AddressForm;
import com.example.phonestore_boot.entity.BuyerAddress;
import com.example.phonestore_boot.repository.BuyerAddressRepository;
import com.example.phonestore_boot.service.AddressService;
import com.example.phonestore_boot.vo.address.AddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private BuyerAddressRepository buyerAddressRepository;
    @Override
    public List<AddressVo> findAll() {
        List<AddressVo> addressVoList = buyerAddressRepository.findAll().stream()
                .map(e -> new AddressVo(
                        e.getAddressId(),
                        e.getAreaCode(),
                        e.getBuyerName(),
                        e.getBuyerPhone(),
                        e.getBuyerAddress()
                )).collect(Collectors.toList());
        return addressVoList;
    }

    @Override
    public void saveOrUpdate(AddressForm addressForm) {
        BuyerAddress buyerAddress;
        if(addressForm.getId() == null ){
            buyerAddress = new BuyerAddress();
        }
        else{
            buyerAddress = buyerAddressRepository.findById(addressForm.getId()).get();
        }
        buyerAddress.setBuyerName(addressForm.getName());
        buyerAddress.setBuyerPhone(addressForm.getTel());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(addressForm.getProvince())
                .append(addressForm.getCity())
                .append(addressForm.getCountry())
                .append(addressForm.getAddressDetail());
        buyerAddress.setBuyerAddress(stringBuffer.toString());
        buyerAddress.setAreaCode(addressForm.getAreaCode());
        buyerAddressRepository.save(buyerAddress);
    }
}
