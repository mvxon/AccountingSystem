package com.bsu.lab.AccountingSystem.util.consolecontrol.availability_of_houses_check;


import com.bsu.lab.AccountingSystem.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityOfHousesCheck {

    private final HouseService houseService;

    @Autowired
    public AvailabilityOfHousesCheck(HouseService houseService) {
        this.houseService = houseService;
    }

    public boolean check() {
        if (houseService.getHousesCount() == 0) {
            System.out.println("Домов нет");
            return true;
        } else return false;
    }
}
