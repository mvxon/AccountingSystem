package com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check;


import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityOfHousesCheck {

    private final HouseRepository houseRepository;

    @Autowired
    public AvailabilityOfHousesCheck(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public boolean check() {
        if (houseRepository.count() == 0) {
            System.out.println("Домов нет");
            return true;
        } else return false;
    }
}
