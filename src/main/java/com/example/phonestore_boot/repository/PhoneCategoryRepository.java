package com.example.phonestore_boot.repository;

import com.example.phonestore_boot.entity.PhoneCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneCategoryRepository extends JpaRepository<PhoneCategory,Integer>{
    PhoneCategory findByCategoryType(Integer categoryType);
}
