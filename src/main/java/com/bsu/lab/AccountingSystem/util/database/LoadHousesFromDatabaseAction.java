package com.bsu.lab.AccountingSystem.util.database;

import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.service.HouseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoadHousesFromDatabaseAction {
    private final HouseRepository houseRepository;
    private final HouseService houseService;

    @Autowired
    public LoadHousesFromDatabaseAction(HouseRepository houseRepository, HouseService houseService) {
        this.houseRepository = houseRepository;
        this.houseService = houseService;
    }


    public void execute(@NotNull Set<House> setOfHouses) {
        if (houseRepository.count() != 0) {
            setOfHouses.addAll(houseRepository.findAll());
            houseService.getUsedHouseNumbers()
                    .addAll(setOfHouses
                            .stream()
                            .map(House::getHouseNumber)
                            .collect(Collectors.toList()));
        }
    }
}
