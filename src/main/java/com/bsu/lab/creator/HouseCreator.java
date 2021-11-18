package com.bsu.lab.creator;

import com.bsu.lab.house.Entrance;
import com.bsu.lab.house.House;
import com.bsu.lab.util.constants.ConstantsForHouseCreating;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class HouseCreator {
    public static @NotNull House CreateHouse() {
        House house = new House();
        house.setEntrancesCount(SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.questionForEntrancesCount));
        if (house.getEntrancesCount() <= 0 || house.getEntrancesCount() > 20)
            do {
                System.out.println(ConstantsForHouseCreating.invalidInputError);
                house.setEntrancesCount(SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.questionForEntrancesCount));
            } while (house.getEntrancesCount() <= 0 || house.getEntrancesCount() > 20);
        for (int i = 0; i < house.getEntrancesCount(); i++) {
            if (i == 0) {
                house.addEntrance(EntranceCreator.createEntrance()); // creating first entrance
            } else {
                // copying first entrance by copy constructor
                house.addEntrance(new Entrance(house.getEntrances().get(0)));
            }
        }
        System.out.println("Дом номер " + house.getHouseNumber() + " успешно добавлен!");
        return house;
    }
}
