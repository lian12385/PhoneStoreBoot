package com.example.phonestore_boot.repository;

import com.example.phonestore_boot.entity.PhoneSpecs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneSpecsRepository extends JpaRepository<PhoneSpecs, Integer> {
    List<PhoneSpecs> findAllByPhoneId(Integer phoneId);
}
