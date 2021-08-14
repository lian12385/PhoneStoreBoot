package com.example.phonestore_boot.repository;

import com.example.phonestore_boot.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BuyerAddressRepositoryTest {

    @Autowired
    private BuyerAddressRepository repository;

    @Test
    void findAll(){
        List<BuyerAddress> list = repository.findAll();
        for (BuyerAddress buyerAddress : list) {
            System.out.println(buyerAddress);
        }
    }

    @Test
    void save(){
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setAreaCode("33311");
        buyerAddress.setBuyerAddress("广东省深圳市");
        buyerAddress.setBuyerPhone("1344321");
        buyerAddress.setBuyerName("小明");
        repository.save(buyerAddress);
    }

    @Test
    void update(){
        BuyerAddress buyerAddress = repository.findById(35).get();
        buyerAddress.setBuyerName("小明明");
        repository.save(buyerAddress);
    }
}