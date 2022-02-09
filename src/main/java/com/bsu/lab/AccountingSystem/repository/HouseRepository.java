package com.bsu.lab.AccountingSystem.repository;

import com.bsu.lab.AccountingSystem.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HouseRepository extends JpaRepository<House, Integer> {
}
