package com.example.phonestore_boot.repository;

import com.example.phonestore_boot.entity.BuyerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerAddressRepository extends JpaRepository<BuyerAddress, Integer> {
    //List<>extends
}
