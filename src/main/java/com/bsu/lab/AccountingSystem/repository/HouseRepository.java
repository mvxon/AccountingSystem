package com.bsu.lab.AccountingSystem.repository;

import com.bsu.lab.AccountingSystem.domain.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.TreeSet;


public interface HouseRepository extends JpaRepository<House, Integer> {

    @Query("select houseNumber from House")
    TreeSet<Integer> findUsedHouseNumbers();


    House findByHouseNumber(int houseNumber);

    @Transactional
    void deleteHouseByHouseNumber(int houseNumber);


}
