package com.bsu.lab.AccountingSystem.dao;

import com.bsu.lab.AccountingSystem.domain.Entrance;
import com.bsu.lab.AccountingSystem.domain.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public interface HouseRepository extends JpaRepository<House, Long> {

    @Query("select houseNumber from House")
    TreeSet<Integer> findUsedHouseNumbers();

    House findByHouseNumber(int houseNumber);

    @Transactional
    void deleteHouseByHouseNumber(int houseNumber);

    House getByEntrancesContains(Entrance entrance);

    @Query("from House where status = 'CREATED' or status = 'CONTINUED'")
    HashSet<House> findAllUnFinishedHouses();

    @Query("from House where status = 'FINISHED'")
    HashSet<House> findAllFinishedHouses();

    @Transactional
    void deleteById(Long id);

}
