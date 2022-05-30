package com.bsu.lab.AccountingSystem.dao;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FloorRepository extends JpaRepository<Floor, Long> {

    Floor getByFlatsContains(Flat flat);
}
