package com.bsu.lab.AccountingSystem.repository;

import com.bsu.lab.AccountingSystem.model.House;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HouseRepository extends JpaRepository<House, Integer> {
}
