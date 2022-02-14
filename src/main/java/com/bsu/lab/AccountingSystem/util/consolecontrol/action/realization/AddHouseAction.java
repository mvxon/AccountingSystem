package com.bsu.lab.AccountingSystem.util.consolecontrol.action.realization;


import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class AddHouseAction {
    private final HouseService houseService;
    private final HouseRepository houseRepository;

    @Autowired
    public AddHouseAction(@Lazy HouseService houseService, HouseRepository houseRepository) {
        this.houseService = houseService;
        this.houseRepository = houseRepository;
    }

    public void execute() {
        House house = houseService.createHouse();
        houseRepository.save(house);
    }
}
