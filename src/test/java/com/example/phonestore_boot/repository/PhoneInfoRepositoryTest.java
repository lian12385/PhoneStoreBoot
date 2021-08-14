package com.example.phonestore_boot.repository;

import com.example.phonestore_boot.entity.PhoneInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneInfoRepositoryTest {
    @Autowired
    private PhoneInfoRepository repository;

    @Test
    void findAll(){
        List<PhoneInfo> list = repository.findAll();
        for(PhoneInfo phoneInfo : list){
            System.out.println(phoneInfo);
        }
    }

    @Test
    void finAllByCategoryType(){
        List<PhoneInfo> list = repository.findAllByCategoryType(1);
        for(PhoneInfo phoneInfo : list){
            System.out.println(phoneInfo);
        }
    }
}