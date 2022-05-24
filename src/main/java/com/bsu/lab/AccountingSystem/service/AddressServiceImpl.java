package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.dao.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
