package com.bsu.lab.creator;

import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.input.creator.InputForEntrancesCount;
import org.jetbrains.annotations.NotNull;

public class HouseCreator {
    public static @NotNull House createHouse() {
        House house = new House();
        int entrancesCount = InputForEntrancesCount.input();
        while (house.getEntrancesCount() < entrancesCount) {
            if (house.getEntrancesCount() == 0) {
                HouseService.addEntrance(house, EntranceCreator.createEntrance()); // creating first entrance
            } else {
                // copying first entrance by copy constructor
                HouseService.addEntrance(house, new Entrance(house.getEntrances().get(0)));
            }
        }
        System.out.println("Дом номер " + house.getHouseNumber() + " успешно добавлен!");
        return house;
    }
}
