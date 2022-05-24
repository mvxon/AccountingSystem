package com.bsu.lab.AccountingSystem.dao;

import com.bsu.lab.AccountingSystem.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Transactional
    void deleteById(Long id);
}
