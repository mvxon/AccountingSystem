package com.bsu.lab.AccountingSystem.dao;

import com.bsu.lab.AccountingSystem.domain.Flat;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FlatRepository extends JpaRepository<Flat, Integer> {
}
