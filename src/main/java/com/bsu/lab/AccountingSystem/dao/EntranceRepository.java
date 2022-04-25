package com.bsu.lab.AccountingSystem.dao;

import com.bsu.lab.AccountingSystem.domain.Entrance;
import com.bsu.lab.AccountingSystem.domain.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntranceRepository extends JpaRepository<Entrance, Integer> {

    Entrance getByFloorsContains(Floor floor);
}
