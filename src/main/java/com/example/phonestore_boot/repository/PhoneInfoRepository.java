package com.example.phonestore_boot.repository;

import com.example.phonestore_boot.entity.PhoneInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneInfoRepository extends JpaRepository<PhoneInfo, Integer> {
    List<PhoneInfo> findAllByCategoryType(Integer categoryType);
}
